(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-service-report-info', {
            parent: 'entity',
            url: '/project-service-report-info',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServiceReportInfo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-infos.html',
                    controller: 'ProjectServiceReportInfoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServiceReportInfo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-service-report-info-detail', {
            parent: 'project-service-report-info',
            url: '/project-service-report-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServiceReportInfo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-info-detail.html',
                    controller: 'ProjectServiceReportInfoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServiceReportInfo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProjectServiceReportInfo', function($stateParams, ProjectServiceReportInfo) {
                    return ProjectServiceReportInfo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-service-report-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-service-report-info-detail.edit', {
            parent: 'project-service-report-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-info-dialog.html',
                    controller: 'ProjectServiceReportInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServiceReportInfo', function(ProjectServiceReportInfo) {
                            return ProjectServiceReportInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-report-info.new', {
            parent: 'project-service-report-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-info-dialog.html',
                    controller: 'ProjectServiceReportInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                projecttype: null,
                                departmentname: null,
                                servicename: null,
                                assignDate: null,
                                requireDate: null,
                                status: null,
                                stage: null,
                                investorName: null,
                                cafPin: null,
                                finalAction: null,
                                finalActionDate: null,
                                projectInvestment: null,
                                projectEmployment: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-service-report-info', null, { reload: 'project-service-report-info' });
                }, function() {
                    $state.go('project-service-report-info');
                });
            }]
        })
        .state('project-service-report-info.edit', {
            parent: 'project-service-report-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-info-dialog.html',
                    controller: 'ProjectServiceReportInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServiceReportInfo', function(ProjectServiceReportInfo) {
                            return ProjectServiceReportInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-report-info', null, { reload: 'project-service-report-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-report-info.delete', {
            parent: 'project-service-report-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-report-info/project-service-report-info-delete-dialog.html',
                    controller: 'ProjectServiceReportInfoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProjectServiceReportInfo', function(ProjectServiceReportInfo) {
                            return ProjectServiceReportInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-report-info', null, { reload: 'project-service-report-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
