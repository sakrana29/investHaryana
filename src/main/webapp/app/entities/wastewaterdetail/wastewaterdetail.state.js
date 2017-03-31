(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('wastewaterdetail', {
            parent: 'entity',
            url: '/wastewaterdetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wastewaterdetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetails.html',
                    controller: 'WastewaterdetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wastewaterdetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('wastewaterdetail-detail', {
            parent: 'wastewaterdetail',
            url: '/wastewaterdetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wastewaterdetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetail-detail.html',
                    controller: 'WastewaterdetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wastewaterdetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Wastewaterdetail', function($stateParams, Wastewaterdetail) {
                    return Wastewaterdetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'wastewaterdetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('wastewaterdetail-detail.edit', {
            parent: 'wastewaterdetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetail-dialog.html',
                    controller: 'WastewaterdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wastewaterdetail', function(Wastewaterdetail) {
                            return Wastewaterdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wastewaterdetail.new', {
            parent: 'wastewaterdetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetail-dialog.html',
                    controller: 'WastewaterdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                source_of_generation: null,
                                quantity: null,
                                description: null,
                                naturetype: null,
                                mode_of_disposal: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('wastewaterdetail', null, { reload: 'wastewaterdetail' });
                }, function() {
                    $state.go('wastewaterdetail');
                });
            }]
        })
        .state('wastewaterdetail.edit', {
            parent: 'wastewaterdetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetail-dialog.html',
                    controller: 'WastewaterdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wastewaterdetail', function(Wastewaterdetail) {
                            return Wastewaterdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wastewaterdetail', null, { reload: 'wastewaterdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wastewaterdetail.delete', {
            parent: 'wastewaterdetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wastewaterdetail/wastewaterdetail-delete-dialog.html',
                    controller: 'WastewaterdetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Wastewaterdetail', function(Wastewaterdetail) {
                            return Wastewaterdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wastewaterdetail', null, { reload: 'wastewaterdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
