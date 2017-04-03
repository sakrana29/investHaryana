(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('modeofdisposalfor-discharge', {
            parent: 'entity',
            url: '/modeofdisposalfor-discharge',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.modeofdisposalfor_discharge.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharges.html',
                    controller: 'Modeofdisposalfor_dischargeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('modeofdisposalfor_discharge');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('modeofdisposalfor-discharge-detail', {
            parent: 'modeofdisposalfor-discharge',
            url: '/modeofdisposalfor-discharge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.modeofdisposalfor_discharge.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharge-detail.html',
                    controller: 'Modeofdisposalfor_dischargeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('modeofdisposalfor_discharge');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Modeofdisposalfor_discharge', function($stateParams, Modeofdisposalfor_discharge) {
                    return Modeofdisposalfor_discharge.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'modeofdisposalfor-discharge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('modeofdisposalfor-discharge-detail.edit', {
            parent: 'modeofdisposalfor-discharge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharge-dialog.html',
                    controller: 'Modeofdisposalfor_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Modeofdisposalfor_discharge', function(Modeofdisposalfor_discharge) {
                            return Modeofdisposalfor_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('modeofdisposalfor-discharge.new', {
            parent: 'modeofdisposalfor-discharge',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharge-dialog.html',
                    controller: 'Modeofdisposalfor_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                disposal_for_discharge: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('modeofdisposalfor-discharge', null, { reload: 'modeofdisposalfor-discharge' });
                }, function() {
                    $state.go('modeofdisposalfor-discharge');
                });
            }]
        })
        .state('modeofdisposalfor-discharge.edit', {
            parent: 'modeofdisposalfor-discharge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharge-dialog.html',
                    controller: 'Modeofdisposalfor_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Modeofdisposalfor_discharge', function(Modeofdisposalfor_discharge) {
                            return Modeofdisposalfor_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('modeofdisposalfor-discharge', null, { reload: 'modeofdisposalfor-discharge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('modeofdisposalfor-discharge.delete', {
            parent: 'modeofdisposalfor-discharge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/modeofdisposalfor-discharge/modeofdisposalfor-discharge-delete-dialog.html',
                    controller: 'Modeofdisposalfor_dischargeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Modeofdisposalfor_discharge', function(Modeofdisposalfor_discharge) {
                            return Modeofdisposalfor_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('modeofdisposalfor-discharge', null, { reload: 'modeofdisposalfor-discharge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
