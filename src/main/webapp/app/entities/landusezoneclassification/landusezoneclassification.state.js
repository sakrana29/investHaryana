(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('landusezoneclassification', {
            parent: 'entity',
            url: '/landusezoneclassification',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.landusezoneclassification.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassifications.html',
                    controller: 'LandusezoneclassificationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('landusezoneclassification');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('landusezoneclassification-detail', {
            parent: 'landusezoneclassification',
            url: '/landusezoneclassification/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.landusezoneclassification.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassification-detail.html',
                    controller: 'LandusezoneclassificationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('landusezoneclassification');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Landusezoneclassification', function($stateParams, Landusezoneclassification) {
                    return Landusezoneclassification.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'landusezoneclassification',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('landusezoneclassification-detail.edit', {
            parent: 'landusezoneclassification-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassification-dialog.html',
                    controller: 'LandusezoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Landusezoneclassification', function(Landusezoneclassification) {
                            return Landusezoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('landusezoneclassification.new', {
            parent: 'landusezoneclassification',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassification-dialog.html',
                    controller: 'LandusezoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                landzoneclassificationtype: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('landusezoneclassification', null, { reload: 'landusezoneclassification' });
                }, function() {
                    $state.go('landusezoneclassification');
                });
            }]
        })
        .state('landusezoneclassification.edit', {
            parent: 'landusezoneclassification',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassification-dialog.html',
                    controller: 'LandusezoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Landusezoneclassification', function(Landusezoneclassification) {
                            return Landusezoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('landusezoneclassification', null, { reload: 'landusezoneclassification' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('landusezoneclassification.delete', {
            parent: 'landusezoneclassification',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landusezoneclassification/landusezoneclassification-delete-dialog.html',
                    controller: 'LandusezoneclassificationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Landusezoneclassification', function(Landusezoneclassification) {
                            return Landusezoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('landusezoneclassification', null, { reload: 'landusezoneclassification' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
