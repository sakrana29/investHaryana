(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-service-log', {
            parent: 'entity',
            url: '/project-service-log',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServiceLog.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-log/project-service-logs.html',
                    controller: 'ProjectServiceLogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServiceLog');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-service-log-detail', {
            parent: 'project-service-log',
            url: '/project-service-log/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServiceLog.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-log/project-service-log-detail.html',
                    controller: 'ProjectServiceLogDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServiceLog');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProjectServiceLog', function($stateParams, ProjectServiceLog) {
                    return ProjectServiceLog.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-service-log',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-service-log-detail.edit', {
            parent: 'project-service-log-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-log/project-service-log-dialog.html',
                    controller: 'ProjectServiceLogDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServiceLog', function(ProjectServiceLog) {
                            return ProjectServiceLog.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-log.new', {
            parent: 'project-service-log',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-log/project-service-log-dialog.html',
                    controller: 'ProjectServiceLogDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                serviceid: null,
                                comments: null,
                                commentDate: null,
                                commentByUserLogin: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-service-log', null, { reload: 'project-service-log' });
                }, function() {
                    $state.go('project-service-log');
                });
            }]
        })
        .state('project-service-log.edit', {
            parent: 'project-service-log',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-log/project-service-log-dialog.html',
                    controller: 'ProjectServiceLogDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServiceLog', function(ProjectServiceLog) {
                            return ProjectServiceLog.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-log', null, { reload: 'project-service-log' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-log.delete', {
            parent: 'project-service-log',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-log/project-service-log-delete-dialog.html',
                    controller: 'ProjectServiceLogDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProjectServiceLog', function(ProjectServiceLog) {
                            return ProjectServiceLog.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-log', null, { reload: 'project-service-log' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
