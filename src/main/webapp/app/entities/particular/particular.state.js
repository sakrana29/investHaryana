(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('particular', {
            parent: 'entity',
            url: '/particular',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.particular.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/particular/particulars.html',
                    controller: 'ParticularController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('particular');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('particular-detail', {
            parent: 'particular',
            url: '/particular/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.particular.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/particular/particular-detail.html',
                    controller: 'ParticularDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('particular');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Particular', function($stateParams, Particular) {
                    return Particular.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'particular',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('particular-detail.edit', {
            parent: 'particular-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/particular/particular-dialog.html',
                    controller: 'ParticularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Particular', function(Particular) {
                            return Particular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('particular.new', {
            parent: 'particular',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/particular/particular-dialog.html',
                    controller: 'ParticularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                particulars: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('particular', null, { reload: 'particular' });
                }, function() {
                    $state.go('particular');
                });
            }]
        })
        .state('particular.edit', {
            parent: 'particular',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/particular/particular-dialog.html',
                    controller: 'ParticularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Particular', function(Particular) {
                            return Particular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('particular', null, { reload: 'particular' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('particular.delete', {
            parent: 'particular',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/particular/particular-delete-dialog.html',
                    controller: 'ParticularDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Particular', function(Particular) {
                            return Particular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('particular', null, { reload: 'particular' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
