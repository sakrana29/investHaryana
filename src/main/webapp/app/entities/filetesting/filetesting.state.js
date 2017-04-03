(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('filetesting', {
            parent: 'entity',
            url: '/filetesting',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.filetesting.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/filetesting/filetestings.html',
                    controller: 'FiletestingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('filetesting');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('filetesting-detail', {
            parent: 'filetesting',
            url: '/filetesting/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.filetesting.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/filetesting/filetesting-detail.html',
                    controller: 'FiletestingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('filetesting');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Filetesting', function($stateParams, Filetesting) {
                    return Filetesting.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'filetesting',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('filetesting-detail.edit', {
            parent: 'filetesting-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filetesting/filetesting-dialog.html',
                    controller: 'FiletestingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Filetesting', function(Filetesting) {
                            return Filetesting.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('filetesting.new', {
            parent: 'filetesting',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filetesting/filetesting-dialog.html',
                    controller: 'FiletestingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                filename: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('filetesting', null, { reload: 'filetesting' });
                }, function() {
                    $state.go('filetesting');
                });
            }]
        })
        .state('filetesting.edit', {
            parent: 'filetesting',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filetesting/filetesting-dialog.html',
                    controller: 'FiletestingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Filetesting', function(Filetesting) {
                            return Filetesting.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('filetesting', null, { reload: 'filetesting' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('filetesting.delete', {
            parent: 'filetesting',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filetesting/filetesting-delete-dialog.html',
                    controller: 'FiletestingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Filetesting', function(Filetesting) {
                            return Filetesting.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('filetesting', null, { reload: 'filetesting' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
