(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'investor','companydetail', 'LoginService', '$state', '$http' , 'Investor','Companydetail'];

    function addprojectController ($scope, Principal, investor,companydetail, LoginService, $state, $http , Investor, Companydetail) {
        var vm = this;
        //vm.statechange=statechange;
        vm.investor=investor;
        vm.companydetail=companydetail;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.saveInvestor=saveInvestor;
        vm.saveCompanyDetail=saveCompanyDetail;
//        vm.getStateByCountry=getStateByCountry;


//        function getStateByCountry()
//            {
//                $http.get("/api/state/country/"+vm.investor.selectedCountry.id).then(function(response) {
//                           vm.states = response.data;
//                       });
//            }
        function saveInvestor()
        {
            vm.isSaving = true;
            vm.investor.countryid=vm.investor.selectedCountry.id;
            vm.investor.stateid=vm.investor.selectedState.id;
            vm.investor.cityid=vm.investor.selectedCity.id;

            Investor.save(vm.investor, onSaveInvestorSuccess, onSaveInvestorError);
        }

        function onSaveInvestorSuccess (resultInvestor) {
                    $scope.$emit('investhryApp:investorUpdate', resultInvestor);
                    //$uibModalInstance.close(result);
                    vm.resultInvestor=resultInvestor;
                    vm.isSaving = false;
                }

                function onSaveInvestorError () {
                    vm.isSaving = false;
                }

        function saveCompanyDetail()
                {
                    vm.isSaving = true;
                    vm.companydetail.investorid=vm.resultInvestor.id;
                    vm.companydetail.businessentitytype=vm.companydetail.selectedBusiness.id;
                    Companydetail.save(vm.companydetail, onSaveCompanySuccess, onSaveCompanyError);
                }
        function onSaveCompanySuccess (resultCompany) {
                    $scope.$emit('investhryApp:companydetailUpdate', resultCompany);
//                    $uibModalInstance.close(result);
                    vm.isSaving = false;
                }

                function onSaveCompanyError () {
                    vm.isSaving = false;
                }

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
        vm.IsVisible = false;
        vm.ShowPassport = function (value) {
            //If DIV is visible it will be hidden and vice versa.
            vm.IsVisible = value == "Y";
        }

       fillFormDataFromEntities();
       function fillFormDataFromEntities()
       {
       $http.get("/api/countries").then(function(response) {
           vm.countries = response.data;
       });
       $http.get("/api/states").then(function(response) {
           vm.states = response.data;
       });
       $http.get("/api/city-town-villages").then(function(response) {
           vm.cities = response.data;
       });
       $http.get("/api/businessentities").then(function(response) {
           vm.businesses = response.data;
       });
       $http.get("/api/sectors").then(function(response) {
           vm.sector = response.data;
       });
       $http.get("/api/industrysizes").then(function(response) {
           vm.sizeindustry = response.data;
       });
       $http.get("/api/projectypes").then(function(response) {
           vm.projecttype = response.data;
       });
       $http.get("/api/projectcategories").then(function(response) {
           vm.categoryproject = response.data;
       });
       $http.get("/api/foreignfundingresources").then(function(response) {
           vm.foreign = response.data;
       });
       $http.get("/api/approvalforms").then(function(response) {
           vm.applicationform = response.data;
       });
        $http.get("/api/blocks").then(function(response) {
           vm.block = response.data;
       });
        $http.get("/api/city-town-villages").then(function(response) {
           vm.city = response.data;
       });
        $http.get("/api/connectingroads").then(function(response) {
           vm.connecting = response.data;
       });
        $http.get("/api/landusezoneclassifications").then(function(response) {
           vm.landzone = response.data;
       });
       $http.get("/api/watersupplysources").then(function(response) {
           vm.watersource = response.data;
       });
       $http.get("/api/waste-water-disposal-modes").then(function(response) {
           vm.wastewatertreatment = response.data;
       });
       $http.get("/api/emmision-pollution-controlls").then(function(response) {
           vm.emissionpolution = response.data;
       });
       $http.get("/api/emmision-fuel-types").then(function(response) {
           vm.emissionfuel = response.data;
       });
       }


    }
})();
