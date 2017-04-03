(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('district', {
            parent: 'entity',
            url: '/district',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.district.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/district/districts.html',
                    controller: 'DistrictController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('district');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('district-detail', {
            parent: 'district',
            url: '/district/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.district.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/district/district-detail.html',
                    controller: 'DistrictDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('district');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'District', function($stateParams, District) {
                    return District.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'district',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('district-detail.edit', {
            parent: 'district-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/district/district-dialog.html',
                    controller: 'DistrictDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['District', function(District) {
                            return District.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('district.new', {
            parent: 'district',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/district/district-dialog.html',
                    controller: 'DistrictDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                districtname: null,
                                statename: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('district', null, { reload: 'district' });
                }, function() {
                    $state.go('district');
                });
            }]
        })
        .state('district.edit', {
            parent: 'district',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/district/district-dialog.html',
                    controller: 'DistrictDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['District', function(District) {
                            return District.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('district', null, { reload: 'district' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('district.delete', {
            parent: 'district',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/district/district-delete-dialog.html',
                    controller: 'DistrictDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['District', function(District) {
                            return District.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('district', null, { reload: 'district' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
