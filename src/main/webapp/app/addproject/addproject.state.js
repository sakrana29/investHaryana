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
                            address1: null,
                            address2: null,
                            address3: null,
                            emailprimary: null,
                            emailsecondary: null,
                            moudocument: null,
                            investorpicpath: null,
                            userlogin: null,
                            cityname: null,
                            countryname: null,
                            statename: null,
                            id: null
                        };
                    },
                 companydetail: function () {
                     return {
                         investorid: null,
                         promoter_md_director: null,
                         designation: null,
                         businessentity: null,
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
                         businessentitytype: null,
                         id: null
                     };
                 },
                 projectdetail: function () {
                     return {
                         projectpurpose: null,
                         niccode: null,
                         existing_regulatory_approval: null,
                         edc_sif_clu_fee_paid_applicable: null,
                         detail_project_report: null,
                         approval_document: null,
                         edc_sif_clu_fee_paid_document: null,
                         investorid: null,
                         approval_application_form: null,
                         category_of_project: null,
                         collaboration_with_foreign_country: null,
                         projectype: null,
                         sectorname: null,
                         size_of_industry: null,
                         cafPIN: null,
                         id: null
                     };
                 },
                     projectsitedetail: function () {
                         return {
                             projectid: null,
                             siteaddress: null,
                             tehsil_subtehsil: null,
                             multyvillageinvolved: null,
                             villageinvolved: null,
                             falls_in_aravalli: null,
                             islandprocured: null,
                             allotedbyhsiidc: null,
                             estate: null,
                             cluster: null,
                             phase: null,
                             sector: null,
                             plotno: null,
                             hadbastno: null,
                             liesunder_mc: null,
                             distance_from_mc: null,
                             islocated_in_urban: null,
                             totalproposedprojectarea: null,
                             proposedbuilt_up_area: null,
                             certifiedownership: null,
                             leaseapplicable: null,
                             landagreementapplicable: null,
                             intersectiondistance: null,
                             railwaydistance: null,
                             confirmitylanduse: null,
                             existing_building_applicable: null,
                             site_situated_in_controlled_area: null,
                             khasra_document: null,
                             revenu_shajra_document: null,
                             jamabandi: null,
                             nonencumbrance_certificate: null,
                             ownership_document: null,
                             lease_document: null,
                             landagreement_document: null,
                             sitelayoutplan: null,
                             locationplan: null,
                             linearstripplan: null,
                             sitesituated_document: null,
                             controlledarea_document: null,
                             buildingexisted: null,
                             district: null,
                             block: null,
                             city_town_village: null,
                             connectingroad: null,
                             landzoneuse_type: null,
                             id: null
                         };
                     },
                     project_finance_investment: function () {
                             return {
                                 projectid: null,
                                 land_cost: null,
                                 building_cost: null,
                                 machinery_cost: null,
                                 misc_assests: null,
                                 total_project_cost: null,
                                 isfdi: null,
                                 fdivalue: null,
                                 project_construction_start_date: null,
                                 commercial_activity_start_date: null,
                                 proposedproject_scheduleid: null,
                                 fdi_country: null,
                                 foreign_funding_source: null,
                                 id: null
                             };
                         },
                         manufacturingdetail: function() {
                             return {
                                 projectid: null,
                                 projectrawmaterialid: null,
                                 productid: null,
                                 processid: null,
                                 manufacturing_flow_document: null,
                                 id: null
                             };
                          },
                          projectrawmaterial: function () {
                              return {
                                  rawmaterial: null,
                                  quantity: null,
                                  units: null,
                                  createdate: null,
                                  updatedate: null,
                                  projectid: null,
                                  id: null
                              };
                          },
                          projectproduct: function () {
                              return {
                                  projectid: null,
                                  mainproduct: null,
                                  quantity: null,
                                  units: null,
                                  id: null
                              };
                          },
                          projectprocessflowstep: function () {
                              return {
                                  projectid: null,
                                  steps: null,
                                  id: null
                              };
                          },
                          environmentimpactdetail: function () {
                              return {
                                  water_process: null,
                                  water_cooling: null,
                                  water_domestic: null,
                                  water_other: null,
                                  waste_water_process: null,
                                  waste_water_cooling: null,
                                  waste_water_domesting: null,
                                  waste_water_other: null,
                                  source_of_water_supply: null,
                                  mode_of_disposal_for_discharge: null,
                                  recycling_process: null,
                                  recycling_cooling: null,
                                  recycling_domestic: null,
                                  recycling_other: null,
                                  createdate: null,
                                  updatedate: null,
                                  sourcewatersupplyother: null,
                                  modedisposalother: null,
                                  id: null
                              };
                          },

                          modeofdisposalfor_discharge: function () {
                              return {
                                  disposal_for_discharge: null,
                                  id: null
                              };
                          },

                          emissiondetail: function () {
                              return {
                                  projectid: null,
                                  particulars: null,
                                  capacity: null,
                                  type_of_fuel: null,
                                  air_pollution_control_device: null,
                                  id: null
                              };
                          },

                          wastewaterdetail: function () {
                              return {
                                  projectid: null,
                                  source_of_generation: null,
                                  naturetype: null,
                                  quantity: null,
                                  mode_of_disposal: null,
                                  description: null,
                                  id: null
                              };
                         },
                         electricrequirement: function () {
                            return {
                                projectid: null,
                                temporaryrequired: null,
                                tem_load_existing: null,
                                tem_account_number: null,
                                temp_existing_load_demand_kw: null,
                                temp_existing_load_demand_kva: null,
                                temp_new_load_demand_kw: null,
                                temp_new_load_demand_kva: null,
                                temp_load_demand_date: null,
                                regular_load_required: null,
                                regular_existing_connection: null,
                                customertype: null,
                                regular_account_number: null,
                                regular_existing_load_ifany_kw: null,
                                regular_existing_load_ifany_kva: null,
                                regular_new_load_demand_kw: null,
                                regular_new_load_demand_kva: null,
                                regular_load_demand_date: null,
                                temporaryconnection: null,
                                regular_connection_doc: null,
                                id: null
                            };
                        },
                        projectcombinecodes: function () {
                            return {
                                investorid: null,
                                companydetailid: null,
                                projectsitedetailid: null,
                                projectfinanceid: null,
                                manufacturingid: null,
                                electricityrequirementid: null,
                                id: null
                            };
                        },
                        treatment1: function () {
                            return {
                                treatment1: null,
                                id: null
                            };
                        },
                        treatment2: function () {
                            return {
                                treatment2: null,
                                id: null
                            };
                        },
                        treatment3: function () {
                            return {
                                treatment3: null,
                                id: null
                            };
                        },
                        term_declaration_accept: function () {
                            return {
                                acceptance: null,
                                applicationdate: null,
                                place: null,
                                createdate: null,
                                updatedate: null,
                                id: null
                            };
                        }
            }
        })
        .state('projectedit', {
            parent: 'app',
            url: '/addproject/edit/{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/addproject/addproject.html',
                    controller: 'editprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                $translatePartialLoader.addPart('addproject');
                return $translate.refresh();
            }],
            entity: ['$stateParams', 'Projectcompletedetail', function($stateParams,Projectcompletedetail) {
                    return Projectcompletedetail.get({id : $stateParams.id}).$promise;
            }]

        }});
    }
})();
