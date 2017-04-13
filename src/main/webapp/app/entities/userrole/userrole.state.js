(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('userrole', {
            parent: 'entity',
            url: '/userrole',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.userrole.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/userrole/userroles.html',
                    controller: 'UserroleController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userrole');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('userrole-detail', {
            parent: 'userrole',
            url: '/userrole/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.userrole.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/userrole/userrole-detail.html',
                    controller: 'UserroleDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userrole');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Userrole', function($stateParams, Userrole) {
                    return Userrole.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'userrole',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('userrole-detail.edit', {
            parent: 'userrole-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/userrole/userrole-dialog.html',
                    controller: 'UserroleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Userrole', function(Userrole) {
                            return Userrole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('userrole.new', {
            parent: 'userrole',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/userrole/userrole-dialog.html',
                    controller: 'UserroleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userrole: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('userrole', null, { reload: 'userrole' });
                }, function() {
                    $state.go('userrole');
                });
            }]
        })
        .state('userrole.edit', {
            parent: 'userrole',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/userrole/userrole-dialog.html',
                    controller: 'UserroleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Userrole', function(Userrole) {
                            return Userrole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('userrole', null, { reload: 'userrole' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('userrole.delete', {
            parent: 'userrole',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/userrole/userrole-delete-dialog.html',
                    controller: 'UserroleDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Userrole', function(Userrole) {
                            return Userrole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('userrole', null, { reload: 'userrole' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
