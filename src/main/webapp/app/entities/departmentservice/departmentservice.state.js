(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('departmentservice', {
            parent: 'entity',
            url: '/departmentservice',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.departmentservice.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/departmentservice/departmentservices.html',
                    controller: 'DepartmentserviceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('departmentservice');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('departmentservice-detail', {
            parent: 'departmentservice',
            url: '/departmentservice/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.departmentservice.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/departmentservice/departmentservice-detail.html',
                    controller: 'DepartmentserviceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('departmentservice');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Departmentservice', function($stateParams, Departmentservice) {
                    return Departmentservice.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'departmentservice',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('departmentservice-detail.edit', {
            parent: 'departmentservice-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/departmentservice/departmentservice-dialog.html',
                    controller: 'DepartmentserviceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Departmentservice', function(Departmentservice) {
                            return Departmentservice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('departmentservice.new', {
            parent: 'departmentservice',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/departmentservice/departmentservice-dialog.html',
                    controller: 'DepartmentserviceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                duration: null,
                                stage: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('departmentservice', null, { reload: 'departmentservice' });
                }, function() {
                    $state.go('departmentservice');
                });
            }]
        })
        .state('departmentservice.edit', {
            parent: 'departmentservice',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/departmentservice/departmentservice-dialog.html',
                    controller: 'DepartmentserviceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Departmentservice', function(Departmentservice) {
                            return Departmentservice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('departmentservice', null, { reload: 'departmentservice' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('departmentservice.delete', {
            parent: 'departmentservice',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/departmentservice/departmentservice-delete-dialog.html',
                    controller: 'DepartmentserviceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Departmentservice', function(Departmentservice) {
                            return Departmentservice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('departmentservice', null, { reload: 'departmentservice' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
