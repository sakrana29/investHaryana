(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('environment-impactdetail', {
            parent: 'entity',
            url: '/environment-impactdetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.environment_impactdetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetails.html',
                    controller: 'Environment_impactdetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('environment_impactdetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('environment-impactdetail-detail', {
            parent: 'environment-impactdetail',
            url: '/environment-impactdetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.environment_impactdetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetail-detail.html',
                    controller: 'Environment_impactdetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('environment_impactdetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Environment_impactdetail', function($stateParams, Environment_impactdetail) {
                    return Environment_impactdetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'environment-impactdetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('environment-impactdetail-detail.edit', {
            parent: 'environment-impactdetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetail-dialog.html',
                    controller: 'Environment_impactdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Environment_impactdetail', function(Environment_impactdetail) {
                            return Environment_impactdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('environment-impactdetail.new', {
            parent: 'environment-impactdetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetail-dialog.html',
                    controller: 'Environment_impactdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                water_process: null,
                                water_cooling: null,
                                water_domestic: null,
                                water_other: null,
                                waste_water_process: null,
                                waste_water_cooling: null,
                                waste_water_domesting: null,
                                waste_water_other: null,
                                waste_water_treatment: null,
                                document_attached: null,
                                other: null,
                                source_of_water_supply: null,
                                mode_of_disposal_for_discharge: null,
                                emissionname: null,
                                wastewaterdetailid: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('environment-impactdetail', null, { reload: 'environment-impactdetail' });
                }, function() {
                    $state.go('environment-impactdetail');
                });
            }]
        })
        .state('environment-impactdetail.edit', {
            parent: 'environment-impactdetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetail-dialog.html',
                    controller: 'Environment_impactdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Environment_impactdetail', function(Environment_impactdetail) {
                            return Environment_impactdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('environment-impactdetail', null, { reload: 'environment-impactdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('environment-impactdetail.delete', {
            parent: 'environment-impactdetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impactdetail/environment-impactdetail-delete-dialog.html',
                    controller: 'Environment_impactdetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Environment_impactdetail', function(Environment_impactdetail) {
                            return Environment_impactdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('environment-impactdetail', null, { reload: 'environment-impactdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
