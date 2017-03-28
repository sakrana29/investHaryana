(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('department-service', {
            parent: 'entity',
            url: '/department-service',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.departmentService.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/department-service/department-services.html',
                    controller: 'DepartmentServiceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('departmentService');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('department-service-detail', {
            parent: 'department-service',
            url: '/department-service/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.departmentService.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/department-service/department-service-detail.html',
                    controller: 'DepartmentServiceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('departmentService');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DepartmentService', function($stateParams, DepartmentService) {
                    return DepartmentService.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'department-service',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('department-service-detail.edit', {
            parent: 'department-service-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/department-service/department-service-dialog.html',
                    controller: 'DepartmentServiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DepartmentService', function(DepartmentService) {
                            return DepartmentService.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('department-service.new', {
            parent: 'department-service',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/department-service/department-service-dialog.html',
                    controller: 'DepartmentServiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                serviceName: null,
                                serviceDescription: null,
                                departmentID: null,
                                duration: null,
                                stage: null,
                                departmentname: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('department-service', null, { reload: 'department-service' });
                }, function() {
                    $state.go('department-service');
                });
            }]
        })
        .state('department-service.edit', {
            parent: 'department-service',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/department-service/department-service-dialog.html',
                    controller: 'DepartmentServiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DepartmentService', function(DepartmentService) {
                            return DepartmentService.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('department-service', null, { reload: 'department-service' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('department-service.delete', {
            parent: 'department-service',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/department-service/department-service-delete-dialog.html',
                    controller: 'DepartmentServiceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DepartmentService', function(DepartmentService) {
                            return DepartmentService.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('department-service', null, { reload: 'department-service' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
