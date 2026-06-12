package org.example.pages.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminPenjadwalanSteps {

    @Given("admin sudah berada di halaman penjadwalan")
    public void admin_sudah_berada_di_halaman_penjadwalan() {
        System.out.println("Admin berada di halaman penjadwalan");
    }

    @When("admin melihat form penjadwalan")
    public void admin_melihat_form_penjadwalan() {
        System.out.println("Form penjadwalan tampil");
    }

    @Then("form penjadwalan berhasil ditampilkan")
    public void form_penjadwalan_berhasil_ditampilkan() {
        System.out.println("Form berhasil ditampilkan");
    }
}