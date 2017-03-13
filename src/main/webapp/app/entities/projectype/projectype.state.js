(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectype', {
            parent: 'entity',
            url: '/projectype',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectype.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectype/projectypes.html',
                    controller: 'ProjectypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectype');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectype-detail', {
            parent: 'projectype',
            url: '/projectype/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectype.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectype/projectype-detail.html',
                    controller: 'ProjectypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectype');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectype', function($stateParams, Projectype) {
                    return Projectype.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectype',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectype-detail.edit', {
            parent: 'projectype-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectype/projectype-dialog.html',
                    controller: 'ProjectypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectype', function(Projectype) {
                            return Projectype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectype.new', {
            parent: 'projectype',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectype/projectype-dialog.html',
                    controller: 'ProjectypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectypes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectype', null, { reload: 'projectype' });
                }, function() {
                    $state.go('projectype');
                });
            }]
        })
        .state('projectype.edit', {
            parent: 'projectype',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectype/projectype-dialog.html',
                    controller: 'ProjectypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectype', function(Projectype) {
                            return Projectype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectype', null, { reload: 'projectype' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectype.delete', {
            parent: 'projectype',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectype/projectype-delete-dialog.html',
                    controller: 'ProjectypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectype', function(Projectype) {
                            return Projectype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectype', null, { reload: 'projectype' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
