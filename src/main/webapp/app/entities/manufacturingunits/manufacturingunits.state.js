(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('manufacturingunits', {
            parent: 'entity',
            url: '/manufacturingunits',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufacturingunits.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits.html',
                    controller: 'ManufacturingunitsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufacturingunits');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('manufacturingunits-detail', {
            parent: 'manufacturingunits',
            url: '/manufacturingunits/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufacturingunits.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits-detail.html',
                    controller: 'ManufacturingunitsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufacturingunits');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Manufacturingunits', function($stateParams, Manufacturingunits) {
                    return Manufacturingunits.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'manufacturingunits',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('manufacturingunits-detail.edit', {
            parent: 'manufacturingunits-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits-dialog.html',
                    controller: 'ManufacturingunitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufacturingunits', function(Manufacturingunits) {
                            return Manufacturingunits.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufacturingunits.new', {
            parent: 'manufacturingunits',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits-dialog.html',
                    controller: 'ManufacturingunitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                unittypes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('manufacturingunits', null, { reload: 'manufacturingunits' });
                }, function() {
                    $state.go('manufacturingunits');
                });
            }]
        })
        .state('manufacturingunits.edit', {
            parent: 'manufacturingunits',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits-dialog.html',
                    controller: 'ManufacturingunitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufacturingunits', function(Manufacturingunits) {
                            return Manufacturingunits.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufacturingunits', null, { reload: 'manufacturingunits' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufacturingunits.delete', {
            parent: 'manufacturingunits',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingunits/manufacturingunits-delete-dialog.html',
                    controller: 'ManufacturingunitsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Manufacturingunits', function(Manufacturingunits) {
                            return Manufacturingunits.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufacturingunits', null, { reload: 'manufacturingunits' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
