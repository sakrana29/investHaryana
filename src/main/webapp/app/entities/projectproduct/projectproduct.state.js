(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectproduct', {
            parent: 'entity',
            url: '/projectproduct',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectproduct.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectproduct/projectproducts.html',
                    controller: 'ProjectproductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectproduct');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectproduct-detail', {
            parent: 'projectproduct',
            url: '/projectproduct/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectproduct.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectproduct/projectproduct-detail.html',
                    controller: 'ProjectproductDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectproduct');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectproduct', function($stateParams, Projectproduct) {
                    return Projectproduct.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectproduct',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectproduct-detail.edit', {
            parent: 'projectproduct-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectproduct/projectproduct-dialog.html',
                    controller: 'ProjectproductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectproduct', function(Projectproduct) {
                            return Projectproduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectproduct.new', {
            parent: 'projectproduct',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectproduct/projectproduct-dialog.html',
                    controller: 'ProjectproductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                mainproduct: null,
                                quantity: null,
                                units: null,
                                projectname: null,
                                unitsname: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectproduct', null, { reload: 'projectproduct' });
                }, function() {
                    $state.go('projectproduct');
                });
            }]
        })
        .state('projectproduct.edit', {
            parent: 'projectproduct',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectproduct/projectproduct-dialog.html',
                    controller: 'ProjectproductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectproduct', function(Projectproduct) {
                            return Projectproduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectproduct', null, { reload: 'projectproduct' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectproduct.delete', {
            parent: 'projectproduct',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectproduct/projectproduct-delete-dialog.html',
                    controller: 'ProjectproductDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectproduct', function(Projectproduct) {
                            return Projectproduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectproduct', null, { reload: 'projectproduct' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
