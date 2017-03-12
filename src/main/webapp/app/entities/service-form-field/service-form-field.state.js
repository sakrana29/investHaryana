(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('service-form-field', {
            parent: 'entity',
            url: '/service-form-field',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.serviceFormField.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/service-form-field/service-form-fields.html',
                    controller: 'ServiceFormFieldController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('serviceFormField');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('service-form-field-detail', {
            parent: 'service-form-field',
            url: '/service-form-field/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.serviceFormField.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/service-form-field/service-form-field-detail.html',
                    controller: 'ServiceFormFieldDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('serviceFormField');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ServiceFormField', function($stateParams, ServiceFormField) {
                    return ServiceFormField.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'service-form-field',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('service-form-field-detail.edit', {
            parent: 'service-form-field-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/service-form-field/service-form-field-dialog.html',
                    controller: 'ServiceFormFieldDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ServiceFormField', function(ServiceFormField) {
                            return ServiceFormField.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('service-form-field.new', {
            parent: 'service-form-field',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/service-form-field/service-form-field-dialog.html',
                    controller: 'ServiceFormFieldDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fieldName: null,
                                fieldType: null,
                                serviceID: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('service-form-field', null, { reload: 'service-form-field' });
                }, function() {
                    $state.go('service-form-field');
                });
            }]
        })
        .state('service-form-field.edit', {
            parent: 'service-form-field',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/service-form-field/service-form-field-dialog.html',
                    controller: 'ServiceFormFieldDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ServiceFormField', function(ServiceFormField) {
                            return ServiceFormField.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('service-form-field', null, { reload: 'service-form-field' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('service-form-field.delete', {
            parent: 'service-form-field',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/service-form-field/service-form-field-delete-dialog.html',
                    controller: 'ServiceFormFieldDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ServiceFormField', function(ServiceFormField) {
                            return ServiceFormField.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('service-form-field', null, { reload: 'service-form-field' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
