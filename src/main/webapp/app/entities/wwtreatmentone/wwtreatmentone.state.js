(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('wwtreatmentone', {
            parent: 'entity',
            url: '/wwtreatmentone',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmentone.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentones.html',
                    controller: 'WwtreatmentoneController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmentone');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('wwtreatmentone-detail', {
            parent: 'wwtreatmentone',
            url: '/wwtreatmentone/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmentone.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentone-detail.html',
                    controller: 'WwtreatmentoneDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmentone');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Wwtreatmentone', function($stateParams, Wwtreatmentone) {
                    return Wwtreatmentone.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'wwtreatmentone',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('wwtreatmentone-detail.edit', {
            parent: 'wwtreatmentone-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentone-dialog.html',
                    controller: 'WwtreatmentoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmentone', function(Wwtreatmentone) {
                            return Wwtreatmentone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmentone.new', {
            parent: 'wwtreatmentone',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentone-dialog.html',
                    controller: 'WwtreatmentoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                treatment1: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentone', null, { reload: 'wwtreatmentone' });
                }, function() {
                    $state.go('wwtreatmentone');
                });
            }]
        })
        .state('wwtreatmentone.edit', {
            parent: 'wwtreatmentone',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentone-dialog.html',
                    controller: 'WwtreatmentoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmentone', function(Wwtreatmentone) {
                            return Wwtreatmentone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentone', null, { reload: 'wwtreatmentone' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmentone.delete', {
            parent: 'wwtreatmentone',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentone/wwtreatmentone-delete-dialog.html',
                    controller: 'WwtreatmentoneDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Wwtreatmentone', function(Wwtreatmentone) {
                            return Wwtreatmentone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentone', null, { reload: 'wwtreatmentone' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
