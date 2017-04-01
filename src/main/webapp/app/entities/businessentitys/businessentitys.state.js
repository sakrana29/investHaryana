(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('businessentitys', {
            parent: 'entity',
            url: '/businessentitys',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.businessentitys.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/businessentitys/businessentitys.html',
                    controller: 'BusinessentitysController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessentitys');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('businessentitys-detail', {
            parent: 'businessentitys',
            url: '/businessentitys/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.businessentitys.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/businessentitys/businessentitys-detail.html',
                    controller: 'BusinessentitysDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessentitys');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Businessentitys', function($stateParams, Businessentitys) {
                    return Businessentitys.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'businessentitys',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('businessentitys-detail.edit', {
            parent: 'businessentitys-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentitys/businessentitys-dialog.html',
                    controller: 'BusinessentitysDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Businessentitys', function(Businessentitys) {
                            return Businessentitys.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('businessentitys.new', {
            parent: 'businessentitys',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentitys/businessentitys-dialog.html',
                    controller: 'BusinessentitysDialogController',
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
                    $state.go('businessentitys', null, { reload: 'businessentitys' });
                }, function() {
                    $state.go('businessentitys');
                });
            }]
        })
        .state('businessentitys.edit', {
            parent: 'businessentitys',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentitys/businessentitys-dialog.html',
                    controller: 'BusinessentitysDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Businessentitys', function(Businessentitys) {
                            return Businessentitys.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('businessentitys', null, { reload: 'businessentitys' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('businessentitys.delete', {
            parent: 'businessentitys',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/businessentitys/businessentitys-delete-dialog.html',
                    controller: 'BusinessentitysDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Businessentitys', function(Businessentitys) {
                            return Businessentitys.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('businessentitys', null, { reload: 'businessentitys' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
