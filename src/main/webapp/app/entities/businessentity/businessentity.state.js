(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('businessentity', {
            parent: 'entity',
            url: '/businessentity',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.businessentity.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/businessentity/businessentities.html',
                    controller: 'BusinessentityController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessentity');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('businessentity-detail', {
            parent: 'businessentity',
            url: '/businessentity/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.businessentity.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/businessentity/businessentity-detail.html',
                    controller: 'BusinessentityDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessentity');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Businessentity', function($stateParams, Businessentity) {
                    return Businessentity.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'businessentity',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('businessentity-detail.edit', {
            parent: 'businessentity-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentity/businessentity-dialog.html',
                    controller: 'BusinessentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Businessentity', function(Businessentity) {
                            return Businessentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('businessentity.new', {
            parent: 'businessentity',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentity/businessentity-dialog.html',
                    controller: 'BusinessentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                businessentitytype: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('businessentity', null, { reload: 'businessentity' });
                }, function() {
                    $state.go('businessentity');
                });
            }]
        })
        .state('businessentity.edit', {
            parent: 'businessentity',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentity/businessentity-dialog.html',
                    controller: 'BusinessentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Businessentity', function(Businessentity) {
                            return Businessentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('businessentity', null, { reload: 'businessentity' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('businessentity.delete', {
            parent: 'businessentity',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentity/businessentity-delete-dialog.html',
                    controller: 'BusinessentityDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Businessentity', function(Businessentity) {
                            return Businessentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('businessentity', null, { reload: 'businessentity' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
