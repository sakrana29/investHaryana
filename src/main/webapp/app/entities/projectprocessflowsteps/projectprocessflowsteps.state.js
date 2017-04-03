(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectprocessflowsteps', {
            parent: 'entity',
            url: '/projectprocessflowsteps',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectprocessflowsteps.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps.html',
                    controller: 'ProjectprocessflowstepsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectprocessflowsteps');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectprocessflowsteps-detail', {
            parent: 'projectprocessflowsteps',
            url: '/projectprocessflowsteps/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectprocessflowsteps.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps-detail.html',
                    controller: 'ProjectprocessflowstepsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectprocessflowsteps');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectprocessflowsteps', function($stateParams, Projectprocessflowsteps) {
                    return Projectprocessflowsteps.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectprocessflowsteps',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectprocessflowsteps-detail.edit', {
            parent: 'projectprocessflowsteps-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps-dialog.html',
                    controller: 'ProjectprocessflowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectprocessflowsteps', function(Projectprocessflowsteps) {
                            return Projectprocessflowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectprocessflowsteps.new', {
            parent: 'projectprocessflowsteps',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps-dialog.html',
                    controller: 'ProjectprocessflowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                steps: null,
                                createdate: null,
                                updatedate: null,
                                projectid: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectprocessflowsteps', null, { reload: 'projectprocessflowsteps' });
                }, function() {
                    $state.go('projectprocessflowsteps');
                });
            }]
        })
        .state('projectprocessflowsteps.edit', {
            parent: 'projectprocessflowsteps',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps-dialog.html',
                    controller: 'ProjectprocessflowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectprocessflowsteps', function(Projectprocessflowsteps) {
                            return Projectprocessflowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectprocessflowsteps', null, { reload: 'projectprocessflowsteps' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectprocessflowsteps.delete', {
            parent: 'projectprocessflowsteps',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocessflowsteps/projectprocessflowsteps-delete-dialog.html',
                    controller: 'ProjectprocessflowstepsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectprocessflowsteps', function(Projectprocessflowsteps) {
                            return Projectprocessflowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectprocessflowsteps', null, { reload: 'projectprocessflowsteps' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
