(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-attachemnt', {
            parent: 'entity',
            url: '/project-attachemnt',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectAttachemnt.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnts.html',
                    controller: 'ProjectAttachemntController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectAttachemnt');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-attachemnt-detail', {
            parent: 'project-attachemnt',
            url: '/project-attachemnt/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectAttachemnt.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnt-detail.html',
                    controller: 'ProjectAttachemntDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectAttachemnt');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProjectAttachemnt', function($stateParams, ProjectAttachemnt) {
                    return ProjectAttachemnt.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-attachemnt',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-attachemnt-detail.edit', {
            parent: 'project-attachemnt-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnt-dialog.html',
                    controller: 'ProjectAttachemntDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectAttachemnt', function(ProjectAttachemnt) {
                            return ProjectAttachemnt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-attachemnt.new', {
            parent: 'project-attachemnt',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnt-dialog.html',
                    controller: 'ProjectAttachemntDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fileName: null,
                                description: null,
                                fileExtension: null,
                                serverFileName: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-attachemnt', null, { reload: 'project-attachemnt' });
                }, function() {
                    $state.go('project-attachemnt');
                });
            }]
        })
        .state('project-attachemnt.edit', {
            parent: 'project-attachemnt',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnt-dialog.html',
                    controller: 'ProjectAttachemntDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectAttachemnt', function(ProjectAttachemnt) {
                            return ProjectAttachemnt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-attachemnt', null, { reload: 'project-attachemnt' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-attachemnt.delete', {
            parent: 'project-attachemnt',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-attachemnt/project-attachemnt-delete-dialog.html',
                    controller: 'ProjectAttachemntDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProjectAttachemnt', function(ProjectAttachemnt) {
                            return ProjectAttachemnt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-attachemnt', null, { reload: 'project-attachemnt' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
