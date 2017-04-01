(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('investor', {
            parent: 'entity',
            url: '/investor',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.investor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/investor/investors.html',
                    controller: 'InvestorController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('investor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('investor-detail', {
            parent: 'investor',
            url: '/investor/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.investor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/investor/investor-detail.html',
                    controller: 'InvestorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('investor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Investor', function($stateParams, Investor) {
                    return Investor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'investor',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('investor-detail.edit', {
            parent: 'investor-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/investor/investor-dialog.html',
                    controller: 'InvestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Investor', function(Investor) {
                            return Investor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('investor.new', {
            parent: 'investor',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/investor/investor-dialog.html',
                    controller: 'InvestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mouapplicable: null,
                                mousignyear: null,
                                mouidnumber: null,
                                firstname: null,
                                middlename: null,
                                lastname: null,
                                address1: null,
                                address2: null,
                                address3: null,
                                emailprimary: null,
                                emailsecondary: null,
                                userlogin: null,
                                cityname: null,
                                countryname: null,
                                statename: null,
                                pincode: null,
                                phonenumber: null,
                                mobilenumber: null,
                                cafpin: null,
                                createdate: null,
                                updatedate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('investor', null, { reload: 'investor' });
                }, function() {
                    $state.go('investor');
                });
            }]
        })
        .state('investor.edit', {
            parent: 'investor',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/investor/investor-dialog.html',
                    controller: 'InvestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Investor', function(Investor) {
                            return Investor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('investor', null, { reload: 'investor' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('investor.delete', {
            parent: 'investor',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/investor/investor-delete-dialog.html',
                    controller: 'InvestorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Investor', function(Investor) {
                            return Investor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('investor', null, { reload: 'investor' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
