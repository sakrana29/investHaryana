(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('wwtreatmenttwo', {
            parent: 'entity',
            url: '/wwtreatmenttwo',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmenttwo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwos.html',
                    controller: 'WwtreatmenttwoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmenttwo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('wwtreatmenttwo-detail', {
            parent: 'wwtreatmenttwo',
            url: '/wwtreatmenttwo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmenttwo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwo-detail.html',
                    controller: 'WwtreatmenttwoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmenttwo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Wwtreatmenttwo', function($stateParams, Wwtreatmenttwo) {
                    return Wwtreatmenttwo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'wwtreatmenttwo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('wwtreatmenttwo-detail.edit', {
            parent: 'wwtreatmenttwo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwo-dialog.html',
                    controller: 'WwtreatmenttwoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmenttwo', function(Wwtreatmenttwo) {
                            return Wwtreatmenttwo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmenttwo.new', {
            parent: 'wwtreatmenttwo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwo-dialog.html',
                    controller: 'WwtreatmenttwoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                treatment2: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('wwtreatmenttwo', null, { reload: 'wwtreatmenttwo' });
                }, function() {
                    $state.go('wwtreatmenttwo');
                });
            }]
        })
        .state('wwtreatmenttwo.edit', {
            parent: 'wwtreatmenttwo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwo-dialog.html',
                    controller: 'WwtreatmenttwoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmenttwo', function(Wwtreatmenttwo) {
                            return Wwtreatmenttwo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmenttwo', null, { reload: 'wwtreatmenttwo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmenttwo.delete', {
            parent: 'wwtreatmenttwo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmenttwo/wwtreatmenttwo-delete-dialog.html',
                    controller: 'WwtreatmenttwoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Wwtreatmenttwo', function(Wwtreatmenttwo) {
                            return Wwtreatmenttwo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmenttwo', null, { reload: 'wwtreatmenttwo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
