(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('wwtreatmentthree', {
            parent: 'entity',
            url: '/wwtreatmentthree',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmentthree.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthrees.html',
                    controller: 'WwtreatmentthreeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmentthree');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('wwtreatmentthree-detail', {
            parent: 'wwtreatmentthree',
            url: '/wwtreatmentthree/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.wwtreatmentthree.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthree-detail.html',
                    controller: 'WwtreatmentthreeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('wwtreatmentthree');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Wwtreatmentthree', function($stateParams, Wwtreatmentthree) {
                    return Wwtreatmentthree.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'wwtreatmentthree',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('wwtreatmentthree-detail.edit', {
            parent: 'wwtreatmentthree-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthree-dialog.html',
                    controller: 'WwtreatmentthreeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmentthree', function(Wwtreatmentthree) {
                            return Wwtreatmentthree.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmentthree.new', {
            parent: 'wwtreatmentthree',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthree-dialog.html',
                    controller: 'WwtreatmentthreeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                treatment3: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentthree', null, { reload: 'wwtreatmentthree' });
                }, function() {
                    $state.go('wwtreatmentthree');
                });
            }]
        })
        .state('wwtreatmentthree.edit', {
            parent: 'wwtreatmentthree',
            url: '{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthree-dialog.html',
                    controller: 'WwtreatmentthreeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Wwtreatmentthree', function(Wwtreatmentthree) {
                            return Wwtreatmentthree.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentthree', null, { reload: 'wwtreatmentthree' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wwtreatmentthree.delete', {
            parent: 'wwtreatmentthree',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wwtreatmentthree/wwtreatmentthree-delete-dialog.html',
                    controller: 'WwtreatmentthreeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Wwtreatmentthree', function(Wwtreatmentthree) {
                            return Wwtreatmentthree.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wwtreatmentthree', null, { reload: 'wwtreatmentthree' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
