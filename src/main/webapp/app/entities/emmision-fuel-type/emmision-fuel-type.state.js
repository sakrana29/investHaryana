(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emmision-fuel-type', {
            parent: 'entity',
            url: '/emmision-fuel-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emmision_fuel_type.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-types.html',
                    controller: 'Emmision_fuel_typeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emmision_fuel_type');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emmision-fuel-type-detail', {
            parent: 'emmision-fuel-type',
            url: '/emmision-fuel-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emmision_fuel_type.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-type-detail.html',
                    controller: 'Emmision_fuel_typeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emmision_fuel_type');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emmision_fuel_type', function($stateParams, Emmision_fuel_type) {
                    return Emmision_fuel_type.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emmision-fuel-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emmision-fuel-type-detail.edit', {
            parent: 'emmision-fuel-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-type-dialog.html',
                    controller: 'Emmision_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emmision_fuel_type', function(Emmision_fuel_type) {
                            return Emmision_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emmision-fuel-type.new', {
            parent: 'emmision-fuel-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-type-dialog.html',
                    controller: 'Emmision_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                typeoffuel: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emmision-fuel-type', null, { reload: 'emmision-fuel-type' });
                }, function() {
                    $state.go('emmision-fuel-type');
                });
            }]
        })
        .state('emmision-fuel-type.edit', {
            parent: 'emmision-fuel-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-type-dialog.html',
                    controller: 'Emmision_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emmision_fuel_type', function(Emmision_fuel_type) {
                            return Emmision_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emmision-fuel-type', null, { reload: 'emmision-fuel-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emmision-fuel-type.delete', {
            parent: 'emmision-fuel-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-fuel-type/emmision-fuel-type-delete-dialog.html',
                    controller: 'Emmision_fuel_typeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emmision_fuel_type', function(Emmision_fuel_type) {
                            return Emmision_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emmision-fuel-type', null, { reload: 'emmision-fuel-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
