package com.hartron.investharyana.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.domain.ProjectServicePaymentDetails;
import com.hartron.investharyana.security.SecurityUtils;
import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.service.ProjectServicePaymentDetailsService;
import com.hartron.investharyana.service.ProjectdetailcombinecodesService;
import com.hartron.investharyana.service.dto.InvestorDTO;
import com.hartron.investharyana.service.dto.PaymentRequestParametersDTO;
import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource {
    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);

    public PaymentResource(InvestorService investorService, ProjectdetailcombinecodesService projectdetailcombinecodesService, ProjectServicePaymentDetailsService projectServicePaymentDetailsService) {
        this.investorService = investorService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
        this.projectServicePaymentDetailsService = projectServicePaymentDetailsService;
    }

    private final InvestorService investorService;
    private final ProjectdetailcombinecodesService projectdetailcombinecodesService;
    private final ProjectServicePaymentDetailsService projectServicePaymentDetailsService;


    @GetMapping("/PaymentParameters/{projectid}/{amount}")
    @Timed
    public ResponseEntity<PaymentRequestParametersDTO> getPaymentParameters(@PathVariable String projectid,@PathVariable String amount) throws Exception {
        log.debug("REST request to get PaymentParameters : {}", projectid);
        PaymentRequestParametersDTO paymentRequestParametersDTO =  new PaymentRequestParametersDTO();

        InvestorDTO investorDTO= investorService.findOne(projectdetailcombinecodesService.findOne(projectid).getInvestorid().toString());
        paymentRequestParametersDTO.setFirstname(investorDTO.getFirstname());
        paymentRequestParametersDTO.setEmail(investorDTO.getEmailprimary());
        paymentRequestParametersDTO.setPhone("9041889636");

        String finalTxnid = generateNewTransactionId();
        paymentRequestParametersDTO.setTxnid(finalTxnid);

        paymentRequestParametersDTO.setProductinfo("Service Payment");
        paymentRequestParametersDTO.setSurl("localhost:8080/api/PaymentParameters");
        paymentRequestParametersDTO.setFurl("api/PaymentParameters");
        paymentRequestParametersDTO.setKey("gtKFFx");

        paymentRequestParametersDTO.setAmount(amount+".00");
        String hash=getHashCode(paymentRequestParametersDTO);
        paymentRequestParametersDTO.setHash(hash);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paymentRequestParametersDTO));
    }

    private String generateNewTransactionId() {
        boolean flag=false;
        String finalTxnid="";
        while(flag==false)
        {
            String temptxnid = RandomStringUtils.randomAlphanumeric(8);
            List<ProjectServicePaymentDetailsDTO> projectServicePaymentDetailsDTOList= projectServicePaymentDetailsService.findAll();
            List<ProjectServicePaymentDetailsDTO> filteredArticleList= projectServicePaymentDetailsDTOList.stream().filter(article -> article.getTransactionId().equals(temptxnid)).collect(Collectors.toList());
            if(filteredArticleList.size()==0)
            {
                flag=true;
                finalTxnid=temptxnid;
            }
        }
        return finalTxnid;
    }

    public String getHashCode(PaymentRequestParametersDTO paymentRequestParametersDTO) throws Exception {
        String combineText = paymentRequestParametersDTO.getKey()+"|"+paymentRequestParametersDTO.getTxnid()+"|"+paymentRequestParametersDTO.getAmount()+"|"+paymentRequestParametersDTO.getProductinfo()+"|"+paymentRequestParametersDTO.getFirstname()+"|"+paymentRequestParametersDTO.getEmail()+"|||||||||||eCwWELxi";
        return hashText(combineText);
    }
    public static String hashText(String textToHash) throws Exception
    {
        final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(textToHash.getBytes());
        return convertByteToHex(sha512.digest());
    }
    public static String convertByteToHex(byte data[])
    {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));

        return hexData.toString();
    }

    @PostMapping("/PaymentParameters")
    @Timed
    public void savePaymentResponse(@RequestBody String paymentResponse) {
        System.out.println(paymentResponse);
    }
}
