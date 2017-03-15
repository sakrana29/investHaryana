(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectsitedetail', {
            parent: 'entity',
            url: '/projectsitedetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectsitedetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetails.html',
                    controller: 'ProjectsitedetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectsitedetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectsitedetail-detail', {
            parent: 'projectsitedetail',
            url: '/projectsitedetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectsitedetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetail-detail.html',
                    controller: 'ProjectsitedetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectsitedetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectsitedetail', function($stateParams, Projectsitedetail) {
                    return Projectsitedetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectsitedetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectsitedetail-detail.edit', {
            parent: 'projectsitedetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetail-dialog.html',
                    controller: 'ProjectsitedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectsitedetail', function(Projectsitedetail) {
                            return Projectsitedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectsitedetail.new', {
            parent: 'projectsitedetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetail-dialog.html',
                    controller: 'ProjectsitedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                siteaddress: null,
                                district: null,
                                block: null,
                                city_town_village: null,
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
                                khasra_document: null,
                                khasra_documentContentType: null,
                                liesunder_mc: null,
                                distance_from_mc: null,
                                islocated_in_urban: null,
                                revenu_shajra_document: null,
                                revenu_shajra_documentContentType: null,
                                jamabandi: null,
                                jamabandiContentType: null,
                                nonencumbrance_certificate: null,
                                nonencumbrance_certificateContentType: null,
                                totalproposedprojectarea: null,
                                proposedbuilt_up_area: null,
                                certifiedownership: null,
                                ownership_document: null,
                                ownership_documentContentType: null,
                                leaseapplicable: null,
                                lease_document: null,
                                lease_documentContentType: null,
                                landagreementapplicable: null,
                                landagreement_document: null,
                                landagreement_documentContentType: null,
                                sitelayoutplan: null,
                                sitelayoutplanContentType: null,
                                locationplan: null,
                                locationplanContentType: null,
                                linearstripplan: null,
                                linearstripplanContentType: null,
                                connectingroad: null,
                                intersectiondistance: null,
                                railwaydistance: null,
                                confirmitylanduse: null,
                                landzoneuse_type: null,
                                sitesituated_document: null,
                                sitesituated_documentContentType: null,
                                buildingexisted: null,
                                existing_building_applicable: null,
                                buildingplan_document: null,
                                buildingplan_documentContentType: null,
                                site_situated_in_controlled_area: null,
                                controlledarea_document: null,
                                controlledarea_documentContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectsitedetail', null, { reload: 'projectsitedetail' });
                }, function() {
                    $state.go('projectsitedetail');
                });
            }]
        })
        .state('projectsitedetail.edit', {
            parent: 'projectsitedetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetail-dialog.html',
                    controller: 'ProjectsitedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectsitedetail', function(Projectsitedetail) {
                            return Projectsitedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectsitedetail', null, { reload: 'projectsitedetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectsitedetail.delete', {
            parent: 'projectsitedetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectsitedetail/projectsitedetail-delete-dialog.html',
                    controller: 'ProjectsitedetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectsitedetail', function(Projectsitedetail) {
                            return Projectsitedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectsitedetail', null, { reload: 'projectsitedetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
