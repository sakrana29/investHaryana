(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('watersupplysource', {
            parent: 'entity',
            url: '/watersupplysource',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.watersupplysource.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/watersupplysource/watersupplysources.html',
                    controller: 'WatersupplysourceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('watersupplysource');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('watersupplysource-detail', {
            parent: 'watersupplysource',
            url: '/watersupplysource/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.watersupplysource.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/watersupplysource/watersupplysource-detail.html',
                    controller: 'WatersupplysourceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('watersupplysource');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Watersupplysource', function($stateParams, Watersupplysource) {
                    return Watersupplysource.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'watersupplysource',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('watersupplysource-detail.edit', {
            parent: 'watersupplysource-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/watersupplysource/watersupplysource-dialog.html',
                    controller: 'WatersupplysourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Watersupplysource', function(Watersupplysource) {
                            return Watersupplysource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('watersupplysource.new', {
            parent: 'watersupplysource',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/watersupplysource/watersupplysource-dialog.html',
                    controller: 'WatersupplysourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                watersupplysourcetype: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('watersupplysource', null, { reload: 'watersupplysource' });
                }, function() {
                    $state.go('watersupplysource');
                });
            }]
        })
        .state('watersupplysource.edit', {
            parent: 'watersupplysource',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/watersupplysource/watersupplysource-dialog.html',
                    controller: 'WatersupplysourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Watersupplysource', function(Watersupplysource) {
                            return Watersupplysource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('watersupplysource', null, { reload: 'watersupplysource' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('watersupplysource.delete', {
            parent: 'watersupplysource',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/watersupplysource/watersupplysource-delete-dialog.html',
                    controller: 'WatersupplysourceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Watersupplysource', function(Watersupplysource) {
                            return Watersupplysource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('watersupplysource', null, { reload: 'watersupplysource' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
