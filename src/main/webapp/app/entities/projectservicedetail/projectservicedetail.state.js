(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectservicedetail', {
            parent: 'entity',
            url: '/projectservicedetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectservicedetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetails.html',
                    controller: 'ProjectservicedetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectservicedetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectservicedetail-detail', {
            parent: 'projectservicedetail',
            url: '/projectservicedetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectservicedetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetail-detail.html',
                    controller: 'ProjectservicedetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectservicedetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectservicedetail', function($stateParams, Projectservicedetail) {
                    return Projectservicedetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectservicedetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectservicedetail-detail.edit', {
            parent: 'projectservicedetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetail-dialog.html',
                    controller: 'ProjectservicedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectservicedetail', function(Projectservicedetail) {
                            return Projectservicedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectservicedetail.new', {
            parent: 'projectservicedetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetail-dialog.html',
                    controller: 'ProjectservicedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                serviceid: null,
                                isRequired: null,
                                requireMarkedOnDate: null,
                                requireMarkedBy: null,
                                isAssigned: null,
                                assigOnDate: null,
                                assignBy: null,
                                formFilledStatus: null,
                                isPaymentMade: null,
                                isPaymentVerified: null,
                                formFilledOnDate: null,
                                formFilledBy: null,
                                paymentMadeOnDate: null,
                                status: null,
                                latestComments: null,
                                serviceFee: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectservicedetail', null, { reload: 'projectservicedetail' });
                }, function() {
                    $state.go('projectservicedetail');
                });
            }]
        })
        .state('projectservicedetail.edit', {
            parent: 'projectservicedetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetail-dialog.html',
                    controller: 'ProjectservicedetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectservicedetail', function(Projectservicedetail) {
                            return Projectservicedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectservicedetail', null, { reload: 'projectservicedetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectservicedetail.delete', {
            parent: 'projectservicedetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectservicedetail/projectservicedetail-delete-dialog.html',
                    controller: 'ProjectservicedetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectservicedetail', function(Projectservicedetail) {
                            return Projectservicedetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectservicedetail', null, { reload: 'projectservicedetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
