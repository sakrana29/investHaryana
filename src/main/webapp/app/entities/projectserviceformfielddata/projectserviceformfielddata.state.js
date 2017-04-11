(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectserviceformfielddata', {
            parent: 'entity',
            url: '/projectserviceformfielddata',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectserviceformfielddata.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata.html',
                    controller: 'ProjectserviceformfielddataController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectserviceformfielddata');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectserviceformfielddata-detail', {
            parent: 'projectserviceformfielddata',
            url: '/projectserviceformfielddata/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectserviceformfielddata.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata-detail.html',
                    controller: 'ProjectserviceformfielddataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectserviceformfielddata');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectserviceformfielddata', function($stateParams, Projectserviceformfielddata) {
                    return Projectserviceformfielddata.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectserviceformfielddata',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectserviceformfielddata-detail.edit', {
            parent: 'projectserviceformfielddata-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata-dialog.html',
                    controller: 'ProjectserviceformfielddataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectserviceformfielddata', function(Projectserviceformfielddata) {
                            return Projectserviceformfielddata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectserviceformfielddata.new', {
            parent: 'projectserviceformfielddata',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata-dialog.html',
                    controller: 'ProjectserviceformfielddataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                serviceid: null,
                                formfieldvalue: null,
                                projectid: null,
                                formfieldName: null,
                                serviceformfieldid: null,
                                formfieldtype: null,
                                formfieldOrder: null,
                                formtypeOption: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectserviceformfielddata', null, { reload: 'projectserviceformfielddata' });
                }, function() {
                    $state.go('projectserviceformfielddata');
                });
            }]
        })
        .state('projectserviceformfielddata.edit', {
            parent: 'projectserviceformfielddata',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata-dialog.html',
                    controller: 'ProjectserviceformfielddataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectserviceformfielddata', function(Projectserviceformfielddata) {
                            return Projectserviceformfielddata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectserviceformfielddata', null, { reload: 'projectserviceformfielddata' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectserviceformfielddata.delete', {
            parent: 'projectserviceformfielddata',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectserviceformfielddata/projectserviceformfielddata-delete-dialog.html',
                    controller: 'ProjectserviceformfielddataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectserviceformfielddata', function(Projectserviceformfielddata) {
                            return Projectserviceformfielddata.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectserviceformfielddata', null, { reload: 'projectserviceformfielddata' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
