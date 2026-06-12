package org.example.steps.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminDashboardSteps {

    @Given("admin membuka halaman dashboard")
    public void admin_membuka_halaman_dashboard() {
        System.out.println("Dashboard dibuka");
    }

    @Then("dashboard admin berhasil ditampilkan")
    public void dashboard_admin_berhasil_ditampilkan() {
        System.out.println("Dashboard tampil");
    }
}