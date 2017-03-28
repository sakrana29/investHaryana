(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('term-declaration-accept', {
            parent: 'entity',
            url: '/term-declaration-accept',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.term_declaration_accept.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accepts.html',
                    controller: 'Term_declaration_acceptController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('term_declaration_accept');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('term-declaration-accept-detail', {
            parent: 'term-declaration-accept',
            url: '/term-declaration-accept/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.term_declaration_accept.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accept-detail.html',
                    controller: 'Term_declaration_acceptDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('term_declaration_accept');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Term_declaration_accept', function($stateParams, Term_declaration_accept) {
                    return Term_declaration_accept.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'term-declaration-accept',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('term-declaration-accept-detail.edit', {
            parent: 'term-declaration-accept-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accept-dialog.html',
                    controller: 'Term_declaration_acceptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Term_declaration_accept', function(Term_declaration_accept) {
                            return Term_declaration_accept.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('term-declaration-accept.new', {
            parent: 'term-declaration-accept',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accept-dialog.html',
                    controller: 'Term_declaration_acceptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                acceptance: null,
                                applicationdate: null,
                                place: null,
                                signature: null,
                                signatureContentType: null,
                                projectname: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('term-declaration-accept', null, { reload: 'term-declaration-accept' });
                }, function() {
                    $state.go('term-declaration-accept');
                });
            }]
        })
        .state('term-declaration-accept.edit', {
            parent: 'term-declaration-accept',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accept-dialog.html',
                    controller: 'Term_declaration_acceptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Term_declaration_accept', function(Term_declaration_accept) {
                            return Term_declaration_accept.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('term-declaration-accept', null, { reload: 'term-declaration-accept' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('term-declaration-accept.delete', {
            parent: 'term-declaration-accept',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/term-declaration-accept/term-declaration-accept-delete-dialog.html',
                    controller: 'Term_declaration_acceptDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Term_declaration_accept', function(Term_declaration_accept) {
                            return Term_declaration_accept.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('term-declaration-accept', null, { reload: 'term-declaration-accept' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
