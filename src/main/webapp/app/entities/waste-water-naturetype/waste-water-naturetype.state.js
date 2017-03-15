(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('waste-water-naturetype', {
            parent: 'entity',
            url: '/waste-water-naturetype',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_naturetype.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetypes.html',
                    controller: 'Waste_water_naturetypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_naturetype');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('waste-water-naturetype-detail', {
            parent: 'waste-water-naturetype',
            url: '/waste-water-naturetype/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_naturetype.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetype-detail.html',
                    controller: 'Waste_water_naturetypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_naturetype');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Waste_water_naturetype', function($stateParams, Waste_water_naturetype) {
                    return Waste_water_naturetype.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'waste-water-naturetype',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('waste-water-naturetype-detail.edit', {
            parent: 'waste-water-naturetype-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetype-dialog.html',
                    controller: 'Waste_water_naturetypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_naturetype', function(Waste_water_naturetype) {
                            return Waste_water_naturetype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-naturetype.new', {
            parent: 'waste-water-naturetype',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetype-dialog.html',
                    controller: 'Waste_water_naturetypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                naturetype: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('waste-water-naturetype', null, { reload: 'waste-water-naturetype' });
                }, function() {
                    $state.go('waste-water-naturetype');
                });
            }]
        })
        .state('waste-water-naturetype.edit', {
            parent: 'waste-water-naturetype',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetype-dialog.html',
                    controller: 'Waste_water_naturetypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_naturetype', function(Waste_water_naturetype) {
                            return Waste_water_naturetype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-naturetype', null, { reload: 'waste-water-naturetype' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-naturetype.delete', {
            parent: 'waste-water-naturetype',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-naturetype/waste-water-naturetype-delete-dialog.html',
                    controller: 'Waste_water_naturetypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Waste_water_naturetype', function(Waste_water_naturetype) {
                            return Waste_water_naturetype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-naturetype', null, { reload: 'waste-water-naturetype' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
