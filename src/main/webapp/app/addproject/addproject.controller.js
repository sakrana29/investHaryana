(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'investor','companydetail','projectdetail','projectsitedetail',
    'electricrequirement','manufacturingdetail','project_finance_investment','projectcombinecodes','LoginService', '$state', 'Investor',
    'Companydetail','Projectdetail','Projectcompletedetail','Country','State','City_town_village','Businessentity','Sector',
    'Industrysize','Projectype','Projectcategory','Foreignfundingresource','Approvalforms','Block','Connectingroad','Landusezoneclassification',
    'Watersupplysource','Waste_water_disposal_mode','Emmision_pollution_controll','Emmision_fuel_type','District','Wwtreatmentone','Wwtreatmenttwo',
    'Wwtreatmentthree','Manufacturingunits','Modeofdisposalfor_discharge','Particular',
    'Waste_water_naturetype'];

    function addprojectController ($scope, Principal, investor, companydetail, projectdetail,projectsitedetail,electricrequirement,
    manufacturingdetail,project_finance_investment,projectcombinecodes, LoginService, $state, Investor, Companydetail, Projectdetail,
    Projectcompletedetail,Country,State,City_town_village,Businessentity,Sector,Industrysize,Projectype,Projectcategory,
    Foreignfundingresource,Approvalforms,Block,Connectingroad,Landusezoneclassification,Watersupplysource,Waste_water_disposal_mode,
    Emmision_pollution_controll,Emmision_fuel_type,District,Wwtreatmentone,Wwtreatmenttwo,Wwtreatmentthree,Manufacturingunits,
    Modeofdisposalfor_discharge,Particular,Waste_water_naturetype)
    {
        var vm = this;
        //vm.statechange=statechange;

        vm.CompleteProjectDetail={};

        vm.investor=investor;
        vm.companydetail=companydetail;
        vm.projectdetail=projectdetail;
        vm.projectsitedetail=projectsitedetail;
        vm.electricrequirement=electricrequirement;
        vm.manufacturingdetail=manufacturingdetail;
        vm.project_finance_investment=project_finance_investment;
        vm.projectcombinecodes=projectcombinecodes;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        vm.saveInvestor=saveInvestor;
        vm.saveCompanyDetail=saveCompanyDetail;
        vm.saveProjectDetail=saveProjectDetail;
        vm.saveCompleteProjectDetail=saveCompleteProjectDetail;

//        vm.CompleteProjectDetail=[];

        function checkDropDowns()
        {
            if (angular.isDefined(vm.investor.selectedCountry))
                vm.investor.countryid=vm.investor.selectedCountry.id;

            if (angular.isDefined(vm.investor.selectedState))
                vm.investor.stateid=vm.investor.selectedState.id;
            if (angular.isDefined(vm.investor.selectedCity))
                vm.investor.cityid=vm.investor.selectedCity.id;

    //            vm.companydetail.investorid=vm.resultInvestor.id;
            if (angular.isDefined(vm.companydetail.selectedBusiness))
                vm.companydetail.businessentitytype=vm.companydetail.selectedBusiness.id;

    //            vm.projectdetail.investorid=vm.resultInvestor.id;
            if (angular.isDefined(vm.projectdetail.selectedSector))
                vm.projectdetail.sectorid=vm.projectdetail.selectedSector.id;
            if (angular.isDefined(vm.projectdetail.selectedSizeOfIndustry))
                vm.projectdetail.size_of_industry=vm.projectdetail.selectedSizeOfIndustry.id;
            if (angular.isDefined(vm.projectdetail.selectedProjectType))
                vm.projectdetail.projectype=vm.projectdetail.selectedProjectType.id;
            if (angular.isDefined(vm.projectdetail.selectedProjectCategory))
                vm.projectdetail.category_of_project=vm.projectdetail.selectedProjectCategory.id;
            if (angular.isDefined(vm.projectdetail.selectedCountry))
                vm.projectdetail.collaboration_with_foreign_country=vm.projectdetail.selectedCountry.id;
            if (angular.isDefined(vm.projectdetail.selectedApprovalForm))
                vm.projectdetail.approval_application_form=vm.projectdetail.selectedApprovalForm.id;

            if (angular.isDefined(vm.projectsitedetail.selectedDistrict))
                vm.projectsitedetail.district=vm.projectsitedetail.selectedDistrict.id;
            if (angular.isDefined(vm.projectsitedetail.selectedBlock))
                vm.projectsitedetail.block=vm.projectsitedetail.selectedBlock.id;
            if (angular.isDefined(vm.projectsitedetail.selectedCityTownVillage))
                vm.projectsitedetail.city_town_village=vm.projectsitedetail.selectedCityTownVillage.id;
            if (angular.isDefined(vm.projectsitedetail.selectedConnectingRoad))
                vm.projectsitedetail.connectingroad=vm.projectsitedetail.selectedConnectingRoad.id;
            if (angular.isDefined(vm.projectsitedetail.selectedLandZoneUseType))
                vm.projectsitedetail.landzoneuse_type=vm.projectsitedetail.selectedLandZoneUseType.id;

            if (angular.isDefined(vm.project_finance_investment.selectedcountryid))
                vm.project_finance_investment.fdi_country=vm.project_finance_investment.selectedcountryid.id;
            if (angular.isDefined(vm.foreignfundingresources.selectedforeignfundingresourceid))
                vm.project_finance_investment.foreign_funding_source= vm.foreignfundingresources.selectedforeignfundingresourceid.id;
        }

        function saveCompleteProjectDetail()
        {
            vm.isSaving = true;
            checkDropDowns();
//
            vm.CompleteProjectDetail.investorDTO=vm.investor;
            vm.CompleteProjectDetail.companydetailDTO=vm.companydetail;
            vm.CompleteProjectDetail.projectdetailDTO=vm.projectdetail;
            vm.CompleteProjectDetail.projectsitedetailDTO=vm.projectsitedetail;
            vm.CompleteProjectDetail.project_finance_investmentDTO=vm.project_finance_investment;
            vm.CompleteProjectDetail.manufacturingdetailDTO=vm.manufacturingdetail;
            vm.CompleteProjectDetail.electricrequirementDTO=vm.electricrequirement;
            vm.CompleteProjectDetail.projectdetailcombinecodesDTO =vm.projectcombinecodes;
//            console.log(vm.CompleteProjectDetail);
            Projectcompletedetail.save(vm.CompleteProjectDetail,onSaveCompleteProjectSuccess,onSaveCompleteProjectError)

        }
        function onSaveCompleteProjectSuccess (resultCompleteProject) {
            $scope.$emit('investhryApp:projectdetailUpdate', resultCompleteProject);
            //$uibModalInstance.close(result);
            vm.resultCompleteProject=resultCompleteProject;
            vm.isSaving = false;
            alert('saved');
            $state.go('listproject');
        }
        function onSaveCompleteProjectError () {
            vm.isSaving = false;
            alert('not saved');
        }

        function saveInvestor()
        {
            vm.isSaving = true;
            vm.investor.countryid=vm.investor.selectedCountry.id;
            vm.investor.stateid=vm.investor.selectedState.id;
            vm.investor.cityid=vm.investor.selectedCity.id;

            Investor.save(vm.investor, onSaveInvestorSuccess, onSaveInvestorError);
        }

        function onSaveInvestorSuccess (resultInvestor) {
            $scope.$emit('investhryApp:investorUpdate', resultInvestor);
            //$uibModalInstance.close(result);
            vm.resultInvestor=resultInvestor;
            vm.isSaving = false;
        }

        function onSaveInvestorError () {
            vm.isSaving = false;
        }

        function saveCompanyDetail()
        {
            vm.isSaving = true;
            vm.companydetail.investorid=vm.resultInvestor.id;
            vm.companydetail.businessentitytype=vm.companydetail.selectedBusiness.id;
            Companydetail.save(vm.companydetail, onSaveCompanySuccess, onSaveCompanyError);
        }
        function onSaveCompanySuccess (resultCompany) {
            $scope.$emit('investhryApp:companydetailUpdate', resultCompany);
//                    $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveCompanyError () {
            vm.isSaving = false;
        }

        function saveProjectDetail()
        {
            vm.isSaving = true;
            vm.projectdetail.investorid=vm.resultInvestor.id;
            vm.projectdetail.sectorid=vm.projectdetail.selectedSector.id;
            vm.projectdetail.size_of_industry=vm.projectdetail.selectedSizeOfIndustry.id;
            vm.projectdetail.projectype=vm.projectdetail.selectedProjectType.id;
            vm.projectdetail.category_of_project=vm.projectdetail.selectedProjectCategory.id;
            vm.projectdetail.collaboration_with_foreign_country=vm.projectdetail.selectedCountry.id;
            vm.projectdetail.approval_application_form=vm.projectdetail.selectedApprovalForm.id;
            Projectdetail.save(vm.projectdetail, onSaveProjectDetailSuccess, onSaveProjectDetailError);
        }

        function onSaveProjectDetailSuccess (resultProjectDetail) {
            $scope.$emit('investhryApp:companydetailUpdate', resultProjectDetail);
    //                    $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveProjectDetailError () {
            vm.isSaving = false;
        }

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
        vm.IsVisible = false;
        vm.ShowPassport = function (value) {
            //If DIV is visible it will be hidden and vice versa.
            vm.IsVisible = value == "Y";
        }

       fillFormDataFromEntities();
       function fillFormDataFromEntities()
       {
       Country.query(function(result) {
           vm.countries = result;
           vm.searchQuery = null;
       });
       State.query(function(result) {
           vm.states = result;
           vm.searchQuery = null;
       });
       District.query(function(result) {
           vm.districts = result;
           vm.searchQuery = null;
       });
       City_town_village.query(function(result) {
           vm.city_town_villages = result;
           vm.searchQuery = null;
       });
       Businessentity.query(function(result) {
           vm.businessentities = result;
           vm.searchQuery = null;
       });
       Sector.query(function(result) {
           vm.sectors = result;
           vm.searchQuery = null;
       });
       Industrysize.query(function(result) {
           vm.industrysizes = result;
           vm.searchQuery = null;
       });
       Projectype.query(function(result) {
           vm.projectypes = result;
           vm.searchQuery = null;
       });
       Projectcategory.query(function(result) {
           vm.projectcategories = result;
           vm.searchQuery = null;
       });
       Foreignfundingresource.query(function(result) {
           vm.foreignfundingresources = result;
           vm.searchQuery = null;
       });
       Approvalforms.query(function(result) {
           vm.approvalforms = result;
           vm.searchQuery = null;
       });
       Block.query(function(result) {
           vm.blocks = result;
           vm.searchQuery = null;
       });
       Connectingroad.query(function(result) {
           vm.connectingroads = result;
           vm.searchQuery = null;
       });
       Landusezoneclassification.query(function(result) {
           vm.landusezoneclassifications = result;
           vm.searchQuery = null;
       });
       Watersupplysource.query(function(result) {
           vm.watersupplysources = result;
           vm.searchQuery = null;
       });
       Waste_water_disposal_mode.query(function(result) {
           vm.waste_water_disposal_modes = result;
               vm.searchQuery = null;
           });
       Emmision_pollution_controll.query(function(result) {
           vm.emmision_pollution_controlls = result;
           vm.searchQuery = null;
       });
       Emmision_fuel_type.query(function(result) {
           vm.emmision_fuel_types = result;
           vm.searchQuery = null;
       });

       Wwtreatmentone.query(function(result) {
              vm.treatment1 = result;
              vm.searchQuery = null;
          });
       Wwtreatmenttwo.query(function(result) {
             vm.treatment2 = result;
             vm.searchQuery = null;
         });
       Wwtreatmentthree.query(function(result) {
            vm.treatment3 = result;
            vm.searchQuery = null;
        });

        Manufacturingunits.query(function(result) {
            vm.manufacturingunits = result;
            vm.searchQuery = null;
          });

        Modeofdisposalfor_discharge.query(function(result) {
             vm.modeofdisposalfor_discharge = result;
             vm.searchQuery = null;
         });
        Particular.query(function(result) {
            vm.particulars = result;
            vm.searchQuery = null;
        });
        Waste_water_naturetype.query(function(result) {
            vm.waste_water_naturetype = result;
            vm.searchQuery = null;
        });
       }
    }
})();
