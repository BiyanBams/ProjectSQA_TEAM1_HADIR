Feature: Import Status Aktif

  @positive
  Scenario: Import file status aktif excel valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman import status aktif
    When User memilih file status aktif excel yang valid
    And User klik button import
    Then Sistem menampilkan message Berhasil import status aktif excel

  @positive
  Scenario: Download template status aktif excel
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman import status aktif
    When User klik button download status aktif
    Then Sistem berhasil mengunduh template berupa file status aktif excel

  @negative
  Scenario: Import file status aktif excel kosong
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman import status aktif
    And User klik button import
    Then Sistem menampilkan pesan error Please select a file di status aktif

  @negative
  Scenario: Import file dengan format salah di status aktif
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import
    And User masuk ke halaman import status aktif
    When User memilih file dengan format salah di status aktif
    And User klik button import 
    Then Sistem menampilkan message format file tidak sesuai di status aktif
