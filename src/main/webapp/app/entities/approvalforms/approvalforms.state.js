(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('approvalforms', {
            parent: 'entity',
            url: '/approvalforms',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.approvalforms.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/approvalforms/approvalforms.html',
                    controller: 'ApprovalformsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('approvalforms');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('approvalforms-detail', {
            parent: 'approvalforms',
            url: '/approvalforms/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.approvalforms.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/approvalforms/approvalforms-detail.html',
                    controller: 'ApprovalformsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('approvalforms');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Approvalforms', function($stateParams, Approvalforms) {
                    return Approvalforms.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'approvalforms',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('approvalforms-detail.edit', {
            parent: 'approvalforms-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/approvalforms/approvalforms-dialog.html',
                    controller: 'ApprovalformsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Approvalforms', function(Approvalforms) {
                            return Approvalforms.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('approvalforms.new', {
            parent: 'approvalforms',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/approvalforms/approvalforms-dialog.html',
                    controller: 'ApprovalformsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                existingapprovalforms: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('approvalforms', null, { reload: 'approvalforms' });
                }, function() {
                    $state.go('approvalforms');
                });
            }]
        })
        .state('approvalforms.edit', {
            parent: 'approvalforms',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/approvalforms/approvalforms-dialog.html',
                    controller: 'ApprovalformsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Approvalforms', function(Approvalforms) {
                            return Approvalforms.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('approvalforms', null, { reload: 'approvalforms' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('approvalforms.delete', {
            parent: 'approvalforms',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/approvalforms/approvalforms-delete-dialog.html',
                    controller: 'ApprovalformsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Approvalforms', function(Approvalforms) {
                            return Approvalforms.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('approvalforms', null, { reload: 'approvalforms' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
