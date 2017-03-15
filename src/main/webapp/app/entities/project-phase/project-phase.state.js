(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-phase', {
            parent: 'entity',
            url: '/project-phase',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_phase.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-phase/project-phases.html',
                    controller: 'Project_phaseController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_phase');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-phase-detail', {
            parent: 'project-phase',
            url: '/project-phase/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_phase.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-phase/project-phase-detail.html',
                    controller: 'Project_phaseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_phase');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Project_phase', function($stateParams, Project_phase) {
                    return Project_phase.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-phase',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-phase-detail.edit', {
            parent: 'project-phase-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-phase/project-phase-dialog.html',
                    controller: 'Project_phaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_phase', function(Project_phase) {
                            return Project_phase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-phase.new', {
            parent: 'project-phase',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-phase/project-phase-dialog.html',
                    controller: 'Project_phaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                phase: null,
                                productcategory: null,
                                fci: null,
                                implementationdate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-phase', null, { reload: 'project-phase' });
                }, function() {
                    $state.go('project-phase');
                });
            }]
        })
        .state('project-phase.edit', {
            parent: 'project-phase',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-phase/project-phase-dialog.html',
                    controller: 'Project_phaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_phase', function(Project_phase) {
                            return Project_phase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-phase', null, { reload: 'project-phase' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-phase.delete', {
            parent: 'project-phase',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-phase/project-phase-delete-dialog.html',
                    controller: 'Project_phaseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Project_phase', function(Project_phase) {
                            return Project_phase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-phase', null, { reload: 'project-phase' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
