Feature: Import Struktur User

  @positive
  Scenario: Import file struktur user excel valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman Struktur User
    When User memilih file struktur user excel yang valid
    And User klik button import
    Then Sistem menampilkan message Berhasil import struktur user excel

  @positive
  Scenario: Download template excel
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman Struktur User
    When User klik button download struktur user 
    Then Sistem berhasil mengunduh template berupa file struktur user excel

  @negative
  Scenario: Import file struktur user excel kosong
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman Struktur User
    And User klik button import
    Then Sistem menampilkan pesan error Please select a file di struktur user

  @negative
  Scenario: Import file struktur dengan format salah
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman Struktur User
    When User memilih file dengan format salah di struktur user
    And User klik button import 
    Then Sistem menampilkan message format file tidak sesuai di struktur user 
