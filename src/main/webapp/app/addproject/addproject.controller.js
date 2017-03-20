(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'investor','companydetail', 'LoginService', '$state', '$http' , 'Investor','Companydetail','ProjectDetail'];

    function addprojectController ($scope, Principal, investor,companydetail,projectdetail, LoginService, $state, $http , Investor, Companydetail,ProjectDetail) {
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

        function saveProjectDetail()
                {
                    vm.isSaving = true;
                    vm.projectdetail.countryid=vm.projectdetail.selectedCountry.id;
                    vm.projectdetail.stateid=vm.projectdetail.selectedState.id;
                    vm.projectdetail.cityid=vm.projectdetail.selectedCity.id;
                    vm.projectdetail.countryid=vm.projectdetail.selectedCountry.id;
                    vm.projectdetail.stateid=vm.projectdetail.selectedState.id;
                    vm.projectdetail.cityid=vm.projectdetail.selectedCity.id;
                    vm.projectdetail.cityid=vm.projectdetail.selectedCity.id;

                    ProjectDetail.save(vm.investor, onSaveProjectDetailSuccess, onSaveProjectDetailError);
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
           vm.sectors = response.data;
       });
       $http.get("/api/industrysizes").then(function(response) {
           vm.industrysizes = response.data;
       });
       $http.get("/api/projectypes").then(function(response) {
           vm.project_types = response.data;
       });
       $http.get("/api/projectcategories").then(function(response) {
           vm.projectcategories = response.data;
       });
       $http.get("/api/foreignfundingresources").then(function(response) {
           vm.foreignfundingresources = response.data;
       });
       $http.get("/api/approvalforms").then(function(response) {
           vm.approvalforms = response.data;
       });
        $http.get("/api/blocks").then(function(response) {
           vm.blocks = response.data;
       });
        $http.get("/api/city-town-villages").then(function(response) {
           vm.city = response.data;
       });
        $http.get("/api/connectingroads").then(function(response) {
           vm.connectingroads = response.data;
       });
        $http.get("/api/landusezoneclassifications").then(function(response) {
           vm.landusezoneclassifications = response.data;
       });
       $http.get("/api/watersupplysources").then(function(response) {
           vm.watersupplysources = response.data;
       });
       $http.get("/api/waste-water-disposal-modes").then(function(response) {
           vm.waste-water-disposal-modes = response.data;
       });
       $http.get("/api/emmision-pollution-controlls").then(function(response) {
           vm.emmision-pollution-controlls = response.data;
       });
       $http.get("/api/emmision-fuel-types").then(function(response) {
           vm.emmision-fuel-types = response.data;
       });
       }


    }
})();
