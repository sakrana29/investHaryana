(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-service-payment-details', {
            parent: 'entity',
            url: '/project-service-payment-details',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServicePaymentDetails.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details.html',
                    controller: 'ProjectServicePaymentDetailsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServicePaymentDetails');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-service-payment-details-detail', {
            parent: 'project-service-payment-details',
            url: '/project-service-payment-details/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectServicePaymentDetails.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details-detail.html',
                    controller: 'ProjectServicePaymentDetailsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectServicePaymentDetails');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProjectServicePaymentDetails', function($stateParams, ProjectServicePaymentDetails) {
                    return ProjectServicePaymentDetails.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-service-payment-details',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-service-payment-details-detail.edit', {
            parent: 'project-service-payment-details-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details-dialog.html',
                    controller: 'ProjectServicePaymentDetailsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServicePaymentDetails', function(ProjectServicePaymentDetails) {
                            return ProjectServicePaymentDetails.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-payment-details.new', {
            parent: 'project-service-payment-details',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details-dialog.html',
                    controller: 'ProjectServicePaymentDetailsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                serviceid: null,
                                paymentMade: null,
                                paymentMadeBy: null,
                                paymentDate: null,
                                transactionId: null,
                                paymentResponse: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-service-payment-details', null, { reload: 'project-service-payment-details' });
                }, function() {
                    $state.go('project-service-payment-details');
                });
            }]
        })
        .state('project-service-payment-details.edit', {
            parent: 'project-service-payment-details',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details-dialog.html',
                    controller: 'ProjectServicePaymentDetailsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProjectServicePaymentDetails', function(ProjectServicePaymentDetails) {
                            return ProjectServicePaymentDetails.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-payment-details', null, { reload: 'project-service-payment-details' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-service-payment-details.delete', {
            parent: 'project-service-payment-details',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-service-payment-details/project-service-payment-details-delete-dialog.html',
                    controller: 'ProjectServicePaymentDetailsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProjectServicePaymentDetails', function(ProjectServicePaymentDetails) {
                            return ProjectServicePaymentDetails.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-service-payment-details', null, { reload: 'project-service-payment-details' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
