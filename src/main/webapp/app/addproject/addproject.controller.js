(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'investor','companydetail','projectdetail','projectsitedetail',
    'electricrequirement','manufacturingdetail','project_finance_investment','projectcombinecodes','LoginService', '$state', 'Investor',
    'Companydetail','Projectdetail','Projectcompletedetail','Country','State','City_town_village','Businessentitys','Sector',
    'Industrysize','Projectype','Projectcategory','Foreignfundingresource','Approvalforms','Block','Connectingroad','Landusezoneclassification',
    'Watersupplysource','Waste_water_disposal_mode','Emmision_pollution_controll','Emmision_fuel_type','District','Wwtreatmentone','Wwtreatmenttwo',
    'Wwtreatmentthree','Manufacturingunits','Modeofdisposalfor_discharge','Particular','Waste_water_naturetype','environmentimpactdetail'];

    function addprojectController ($scope, Principal, investor, companydetail, projectdetail,projectsitedetail,electricrequirement,
    manufacturingdetail,project_finance_investment,projectcombinecodes, LoginService, $state, Investor, Companydetail, Projectdetail,
    Projectcompletedetail,Country,State,City_town_village,Businessentitys,Sector,Industrysize,Projectype,Projectcategory,
    Foreignfundingresource,Approvalforms,Block,Connectingroad,Landusezoneclassification,Watersupplysource,Waste_water_disposal_mode,
    Emmision_pollution_controll,Emmision_fuel_type,District,Wwtreatmentone,Wwtreatmenttwo,Wwtreatmentthree,Manufacturingunits,
    Modeofdisposalfor_discharge,Particular,Waste_water_naturetype,environmentimpactdetail)
    {
        var vm = this;

        vm.CompleteProjectDetail={};

        vm.completeprojectphasedata=[];
        vm.addProject_phaseData=addProject_phaseData;
        function addProject_phaseData(){
          vm.project_phase.projectid="";
          vm.completeprojectphasedata.push(vm.project_phase);
          vm.project_phase={};
        }
         // delete the selected rows from table
        vm.removeRow = function(phase){
            var index = -1;
            var comArr =eval(vm.completeprojectphasedata);

            for( var i = 0; i < comArr.length; i++ ) {
                if( comArr[i].phase=== phase) {
                    index = i;
                    break;
                }
            }
           vm.completeprojectphasedata.splice( index, 1 );
          };

       //Start raw material
        vm.projectrawmaterialdata=[];
        vm.addProject_rawmaterialData=addProject_rawmaterialData;
              function addProject_rawmaterialData(){
                  if(angular.isDefined(vm.project_rawmaterial.selectedUnit))
                  {
                      console.log(vm.project_rawmaterial.selectedUnit.unittypes);
                      vm.project_rawmaterial.units=vm.project_rawmaterial.selectedUnit.unittypes;
                      console.log(vm.project_rawmaterial.units);
                  }
                  else
                  {
                      vm.project_rawmaterial.units="";
                  }

                 vm.projectrawmaterialdata.push(vm.project_rawmaterial);
                               //reinstantiate your $scope.formVariable so that your form is empty
                 vm.project_rawmaterial={};
               }

             vm.removeRawRow = function(rawmaterial){
                 var index = -1;
                 var comArr =eval(vm.projectrawmaterialdata);

                 for( var i = 0; i < comArr.length; i++ ) {
                     if( comArr[i].rawmaterial=== rawmaterial) {
                         index = i;
                         break;
                     }
                 }
                vm.projectrawmaterialdata.splice( index, 1 );
              };

            // Start main product
       vm.projectmainproductdata=[];
       vm.addProject_mainproductData=addProject_mainproductData;

            function addProject_mainproductData(){
                if(angular.isDefined(vm.product.selectedUnit))
                {
                     vm.product.units=vm.product.selectedUnit.unittypes;
                }
                else
                {
                   vm.product.units="";
                }
                vm.projectmainproductdata.push(vm.product);
                //reinstantiate your $scope.formVariable so that your form is empty
                vm.product={};
            }
       vm.removeMainRow = function(mainproduct){
                var index = -1;
                var comArr =eval(vm.projectmainproductdata);

                for( var i = 0; i < comArr.length; i++ ) {
                    if(comArr[i].mainproduct=== mainproduct) {
                        index = i;
                        break;
                    }
                }
                vm.projectmainproductdata.splice( index, 1 );
       };

                  //End main product

       vm.projectprocessflowstepdata=[];
       vm.addProject_processflowstepData=addProject_processflowstepData;
       function addProject_processflowstepData(){
           vm.projectprocessflowstepdata.push(vm.projectprocessflowstep);
           //reinstantiate your $scope.formVariable so that your form is empty
          vm.projectprocessflowstep={};
       }
       vm.removeStepRow = function(steps){
            var index = -1;
            var comArr =eval(vm.projectprocessflowstepdata);
              for( var i = 0; i < comArr.length; i++ ) {
                if( comArr[i].steps=== steps) {
                    index = i;
                    break;
                }
              }
          vm.projectprocessflowstepdata.splice( index, 1 );
       };

                        // Start emission detail
       vm.emissiondetaildata=[];
       vm.addProject_emissiondetailData=addProject_emissiondetailData;
       function addProject_emissiondetailData() {
              if(angular.isDefined(vm.emissiondetail.selectedParticular))
               {
               vm.emissiondetail.particulars=vm.emissiondetail.selectedParticular.particulars;
               }
              else
               {
              vm.emissiondetail.particulars="";
               }

               if(angular.isDefined(vm.emissiondetail.selectedTypeoffuel))
                {
                   vm.emissiondetail.type_of_fuel=vm.emissiondetail.selectedTypeoffuel.typeoffuel;
                }
               else
                {
                  vm.emissiondetail.type_of_fuel="";
                }

               if(angular.isDefined(vm.emissiondetail.selectedAirpollutioncontroldevice))
                {
                 vm.emissiondetail.air_pollution_control_device=vm.emissiondetail.selectedAirpollutioncontroldevice.airpollutioncontroldevice;
                 }
               else
                {
               vm.emissiondetail.air_pollution_control_device="";
                }

             vm.emissiondetaildata.push(vm.emissiondetail);
            //reinstantiate your $scope.formVariable so that your form is empty
            vm.emissiondetail={};
       }

       vm.removeEmissionRow = function(selectedParticular){
                       var index = -1;
                       var comArr =eval(vm.emissiondetaildata);

                       for( var i = 0; i < comArr.length; i++ ) {
                           if( comArr[i].selectedParticular=== selectedParticular) {
                               index = i;
                               break;
                           }
                       }
                 vm.emissiondetaildata.splice( index, 1 )
       };

           // End emission detail

          // Start waste water detail
       vm.wastewaterdetaildata=[];
       vm.addProject_wastewaterdetailData=addProject_wastewaterdetailData;
       function addProject_wastewaterdetailData(){
              if(angular.isDefined(vm.wastewaterdetail.selectedNaturetype))
              {
               vm.wastewaterdetail.naturetype=vm.wastewaterdetail.selectedNaturetype.naturetype;
               console.log(vm.wastewaterdetail.selectedNaturetype.naturetype);
                   }
               else
                 {
                vm.wastewaterdetail.naturetype="";
                  }

               if(angular.isDefined(vm.wastewaterdetail.selectedModeofdisposal))
               {
                 vm.wastewaterdetail.mode_of_disposal=vm.wastewaterdetail.selectedModeofdisposal.disposal_for_discharge
                  }
               else
                 {
               vm.wastewaterdetail.mode_of_disposal="";
                  }
         vm.wastewaterdetaildata.push(vm.wastewaterdetail);
         vm.wastewaterdetail={};
       }
       vm.removeSourceRow = function(source_of_generation){
          var index = -1;
          var comArr =eval(vm.wastewaterdetaildata);

          for( var i = 0; i < comArr.length; i++ ) {
              if( comArr[i].source_of_generation=== source_of_generation) {
                  index = i;
                  break;
              }
          }
          vm.wastewaterdetaildata.splice( index, 1 );
         };
//End waste water

        vm.investor=investor;
        vm.companydetail=companydetail;
        vm.projectdetail=projectdetail;
        vm.projectsitedetail=projectsitedetail;
        vm.electricrequirement=electricrequirement;
        vm.manufacturingdetail=manufacturingdetail;
        vm.project_finance_investment=project_finance_investment;
        vm.projectcombinecodes=projectcombinecodes;
        vm.environmentimpactdetail=environmentimpactdetail;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

//        vm.saveInvestor=saveInvestor;
//        vm.saveCompanyDetail=saveCompanyDetail;
//        vm.saveProjectDetail=saveProjectDetail;
        vm.saveCompleteProjectDetail=saveCompleteProjectDetail;

        function checkDropDowns()
        {
            if (angular.isDefined(vm.investor.selectedCountry))
                vm.investor.countryname=vm.investor.selectedCountry.countryname;
            if (angular.isDefined(vm.investor.selectedState))
                vm.investor.statename=vm.investor.selectedState.statename;
            if (angular.isDefined(vm.investor.selectedCity))
                vm.investor.cityname=vm.investor.selectedCity.city_town_village_name;

            if (angular.isDefined(vm.companydetail.selectedBusiness))
                vm.companydetail.businessentitytype=vm.companydetail.selectedBusiness.businessentitytype;

            if (angular.isDefined(vm.projectdetail.selectedSector))
                vm.projectdetail.sectorname=vm.projectdetail.selectedSector.sectortype;
            if (angular.isDefined(vm.projectdetail.selectedSizeOfIndustry))
                vm.projectdetail.size_of_industry=vm.projectdetail.selectedSizeOfIndustry.sizeofindustry;
            if (angular.isDefined(vm.projectdetail.selectedProjectType))
                vm.projectdetail.projectype=vm.projectdetail.selectedProjectType.projectypes;
            if (angular.isDefined(vm.projectdetail.selectedProjectCategory))
                vm.projectdetail.category_of_project=vm.projectdetail.selectedProjectCategory.categorytype;
            if (angular.isDefined(vm.projectdetail.selectedCountry))
                vm.projectdetail.collaboration_with_foreign_country=vm.projectdetail.selectedCountry.countryname;
            if (angular.isDefined(vm.projectdetail.selectedApprovalForm))
                vm.projectdetail.approval_application_form=vm.projectdetail.selectedApprovalForm.existingapprovalforms;

            if (angular.isDefined(vm.projectsitedetail.selectedDistrict))
                vm.projectsitedetail.district=vm.projectsitedetail.selectedDistrict.districtname;
            if (angular.isDefined(vm.projectsitedetail.selectedBlock))
                vm.projectsitedetail.block=vm.projectsitedetail.selectedBlock.blockname;
            if (angular.isDefined(vm.projectsitedetail.selectedCityTownVillage))
                vm.projectsitedetail.city_town_village=vm.projectsitedetail.selectedCityTownVillage.city_town_village_name;
            if (angular.isDefined(vm.projectsitedetail.selectedConnectingRoad))
                vm.projectsitedetail.connectingroad=vm.projectsitedetail.selectedConnectingRoad.connectingraodtype;
            if (angular.isDefined(vm.projectsitedetail.selectedLandZoneUseType))
                vm.projectsitedetail.landzoneuse_type=vm.projectsitedetail.selectedLandZoneUseType.landzoneclassificationtype;

            if (angular.isDefined(vm.project_finance_investment.selectedcountryid))
                vm.project_finance_investment.fdi_country=vm.project_finance_investment.selectedcountryid.countryname;
            if (angular.isDefined(vm.foreignfundingresources.selectedforeignfundingresourceid))
                vm.project_finance_investment.foreign_funding_source= vm.foreignfundingresources.selectedforeignfundingresourceid.foreignfundingtypes;

            if (angular.isDefined(vm.environmentimpactdetail.selectedWatersource))
                vm.environmentimpactdetail.source_of_water_supply=vm.environmentimpactdetail.selectedWatersource.watersupplysourcetype;
            if (angular.isDefined(vm.environmentimpactdetail.selectedTreatmentone))
                vm.environmentimpactdetail.recycling_process= vm.environmentimpactdetail.selectedTreatmentone.treatment1;
            if (angular.isDefined(vm.environmentimpactdetail.selectedTreatmenttwo))
                vm.environmentimpactdetail.recycling_cooling=vm.environmentimpactdetail.selectedTreatmenttwo.treatment2;
            if (angular.isDefined(vm.environmentimpactdetail.selectedTreatmentthree))
                vm.environmentimpactdetail.recycling_domestic= vm.environmentimpactdetail.selectedTreatmentthree.treatment3;
            if (angular.isDefined(vm.environmentimpactdetail.selectedDischarge))
                vm.environmentimpactdetail.mode_of_disposal_for_discharge= vm.environmentimpactdetail.selectedDischarge.disposal_for_discharge;

        }

        function saveCompleteProjectDetail(flag)
        {
            vm.isSaving = true;
            checkDropDowns();
            if(flag=='Final')
            {
                vm.investor.cafpin=1;
            }
            vm.CompleteProjectDetail.investorDTO=vm.investor;
            vm.CompleteProjectDetail.companydetailDTO=vm.companydetail;
            vm.CompleteProjectDetail.projectdetailDTO=vm.projectdetail;
            vm.CompleteProjectDetail.projectsitedetailDTO=vm.projectsitedetail;
            vm.CompleteProjectDetail.project_finance_investmentDTO=vm.project_finance_investment;
            vm.CompleteProjectDetail.manufacturingdetailDTO=vm.manufacturingdetail;
            vm.CompleteProjectDetail.environment_impactdetailDTO=vm.environmentimpactdetail;
            vm.CompleteProjectDetail.electricrequirementDTO=vm.electricrequirement;
            vm.CompleteProjectDetail.projectdetailcombinecodesDTO =vm.projectcombinecodes;
            vm.CompleteProjectDetail.project_phaseDTOList=vm.completeprojectphasedata;
            vm.CompleteProjectDetail.projectrawmaterialDTOList=vm.projectrawmaterialdata;

//            console.log(vm.CompleteProjectDetail.projectrawmaterialDTOList);

            vm.CompleteProjectDetail.projectproductDTOList=vm.projectmainproductdata;
            vm.CompleteProjectDetail.projectprocessflowstepsDTOList=vm.projectprocessflowstepdata;
            vm.CompleteProjectDetail.emissiondetailDTOList=vm.emissiondetaildata;
            vm.CompleteProjectDetail.wastewaterdetailDTOList= vm.wastewaterdetaildata;

//            vm.project_rawmaterial;
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
       Businessentitys.query(function(result) {
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
