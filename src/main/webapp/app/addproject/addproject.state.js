(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('addproject', {
            parent: 'app',
            url: '/addproject',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/addproject/addproject.html',
                    controller: 'addprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('addproject');
                    return $translate.refresh();
                }],
                investor: function () {
                        return {
                            mouapplicable: null,
                            mousignyear: null,
                            mouidnumber: null,
                            firstname: null,
                            middlename: null,
                            lastname: null,
                            countryid: null,
                            stateid: null,
                            cityid: null,
                            address1: null,
                            address2: null,
                            address3: null,
                            emailprimary: null,
                            emailsecondary: null,
                            moudocument: null,
                            investorpicpath: null,
                            userlogin: null,
                            id: null
                        };
                    },
                 companydetail: function () {
                     return {
                         investorid: null,
                         promoter_md_director: null,
                         designation: null,
                         businessentity: null,
                         businessentitytype: null,
                         director_promoter_md_ceo_number: null,
                         pan_number: null,
                         aadhar_number: null,
                         nri: null,
                         tin_vat_number: null,
                         cst_number: null,
                         director_md_ceo_list: null,
                         pancard: null,
                         aadharcard: null,
                         tin_vat_document: null,
                         cst_document: null,
                         moa_partnershipdeed: null,
                         registration_document: null,
                         id: null
                     };
                 },
                 projectdetail: function () {
                     return {
                         investorid: null,
                         sector: null,
                         projectpurpose: null,
                         size_of_industry: null,
                         projectype: null,
                         niccode: null,
                         category_of_project: null,
                         collaboration_with_foreign_country: null,
                         existing_regulatory_approval: null,
                         approval_application_form: null,
                         edc_sif_clu_fee_paid_applicable: null,
                         detail_project_report: null,
                         approval_document: null,
                         edc_sif_clu_fee_paid_document: null,
                         id: null
                     };
                 }

            }
        });
    }
})();
