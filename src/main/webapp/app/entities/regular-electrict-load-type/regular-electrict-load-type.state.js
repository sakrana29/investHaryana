(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('regular-electrict-load-type', {
            parent: 'entity',
            url: '/regular-electrict-load-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.regular_electrict_load_type.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-types.html',
                    controller: 'Regular_electrict_load_typeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('regular_electrict_load_type');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('regular-electrict-load-type-detail', {
            parent: 'regular-electrict-load-type',
            url: '/regular-electrict-load-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.regular_electrict_load_type.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-type-detail.html',
                    controller: 'Regular_electrict_load_typeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('regular_electrict_load_type');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Regular_electrict_load_type', function($stateParams, Regular_electrict_load_type) {
                    return Regular_electrict_load_type.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'regular-electrict-load-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('regular-electrict-load-type-detail.edit', {
            parent: 'regular-electrict-load-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-type-dialog.html',
                    controller: 'Regular_electrict_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Regular_electrict_load_type', function(Regular_electrict_load_type) {
                            return Regular_electrict_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('regular-electrict-load-type.new', {
            parent: 'regular-electrict-load-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-type-dialog.html',
                    controller: 'Regular_electrict_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                typeofload: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('regular-electrict-load-type', null, { reload: 'regular-electrict-load-type' });
                }, function() {
                    $state.go('regular-electrict-load-type');
                });
            }]
        })
        .state('regular-electrict-load-type.edit', {
            parent: 'regular-electrict-load-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-type-dialog.html',
                    controller: 'Regular_electrict_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Regular_electrict_load_type', function(Regular_electrict_load_type) {
                            return Regular_electrict_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('regular-electrict-load-type', null, { reload: 'regular-electrict-load-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('regular-electrict-load-type.delete', {
            parent: 'regular-electrict-load-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/regular-electrict-load-type/regular-electrict-load-type-delete-dialog.html',
                    controller: 'Regular_electrict_load_typeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Regular_electrict_load_type', function(Regular_electrict_load_type) {
                            return Regular_electrict_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('regular-electrict-load-type', null, { reload: 'regular-electrict-load-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
