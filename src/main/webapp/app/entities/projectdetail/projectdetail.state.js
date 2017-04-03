(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectdetail', {
            parent: 'entity',
            url: '/projectdetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectdetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectdetail/projectdetails.html',
                    controller: 'ProjectdetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectdetail-detail', {
            parent: 'projectdetail',
            url: '/projectdetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectdetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectdetail/projectdetail-detail.html',
                    controller: 'ProjectdetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectdetail', function($stateParams, Projectdetail) {
                    return Projectdetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectdetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectdetail-detail.edit', {
            parent: 'projectdetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetail/projectdetail-dialog.html',
                    controller: 'ProjectdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectdetail', function(Projectdetail) {
                            return Projectdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectdetail.new', {
            parent: 'projectdetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetail/projectdetail-dialog.html',
                    controller: 'ProjectdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
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
                        }
                    }
                }).result.then(function() {
                    $state.go('projectdetail', null, { reload: 'projectdetail' });
                }, function() {
                    $state.go('projectdetail');
                });
            }]
        })
        .state('projectdetail.edit', {
            parent: 'projectdetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetail/projectdetail-dialog.html',
                    controller: 'ProjectdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectdetail', function(Projectdetail) {
                            return Projectdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectdetail', null, { reload: 'projectdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectdetail.delete', {
            parent: 'projectdetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetail/projectdetail-delete-dialog.html',
                    controller: 'ProjectdetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectdetail', function(Projectdetail) {
                            return Projectdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectdetail', null, { reload: 'projectdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
