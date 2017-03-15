(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emmision-pollution-controll', {
            parent: 'entity',
            url: '/emmision-pollution-controll',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emmision_pollution_controll.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controlls.html',
                    controller: 'Emmision_pollution_controllController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emmision_pollution_controll');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emmision-pollution-controll-detail', {
            parent: 'emmision-pollution-controll',
            url: '/emmision-pollution-controll/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emmision_pollution_controll.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controll-detail.html',
                    controller: 'Emmision_pollution_controllDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emmision_pollution_controll');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emmision_pollution_controll', function($stateParams, Emmision_pollution_controll) {
                    return Emmision_pollution_controll.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emmision-pollution-controll',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emmision-pollution-controll-detail.edit', {
            parent: 'emmision-pollution-controll-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controll-dialog.html',
                    controller: 'Emmision_pollution_controllDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emmision_pollution_controll', function(Emmision_pollution_controll) {
                            return Emmision_pollution_controll.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emmision-pollution-controll.new', {
            parent: 'emmision-pollution-controll',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controll-dialog.html',
                    controller: 'Emmision_pollution_controllDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                airpollutioncontroldevice: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emmision-pollution-controll', null, { reload: 'emmision-pollution-controll' });
                }, function() {
                    $state.go('emmision-pollution-controll');
                });
            }]
        })
        .state('emmision-pollution-controll.edit', {
            parent: 'emmision-pollution-controll',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controll-dialog.html',
                    controller: 'Emmision_pollution_controllDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emmision_pollution_controll', function(Emmision_pollution_controll) {
                            return Emmision_pollution_controll.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emmision-pollution-controll', null, { reload: 'emmision-pollution-controll' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emmision-pollution-controll.delete', {
            parent: 'emmision-pollution-controll',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emmision-pollution-controll/emmision-pollution-controll-delete-dialog.html',
                    controller: 'Emmision_pollution_controllDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emmision_pollution_controll', function(Emmision_pollution_controll) {
                            return Emmision_pollution_controll.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emmision-pollution-controll', null, { reload: 'emmision-pollution-controll' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
