Feature: Import Cuti

  @positive
  Scenario: import file excel dengan tanggal cuti valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file excel yang valid
    And User klik button import
    Then Sistem menampilkan message Berhasil import excel
    Then Sistem menampilkan jumlah sukses dan gagal import

  @positive
  Scenario: download template cuti
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User klik button download template
    Then Sistem berhasil mengunduh template berupa file excel

  @negative
  Scenario: import tanpa memasukkan file
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    And User klik button import
    Then Sistem menampilkan pesan error Please select a file

  @negative
  Scenario: import file dengan memasukkan file selain excel: "pdf"
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file dengan format salah
    And User klik button import
    Then Sistem menampilkan message format file tidak sesuai

  @negative
  Scenario: import file excel dengan sisa cuti habis atau kosong
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file excel cuti kosong
    And User klik button import
    Then Sistem menampilkan message Berhasil import excel
    Then Sistem menampilkan logs jumlah sukses dan gagal import

@negative
  Scenario: import file excel dengan tanggal cuti invalid (tahun cuti dibuat invalid)
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file excel cuti tahun invalid
    And User klik button import
    Then Sistem menampilkan Berhasil import excel
    Then Sistem menampilkan logs sukses dan gagal import


@negative
  Scenario: import file excel dengan nik invalid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file excel cuti nik invalid
    And User klik button import
    Then Sistem menampilkan gagal import file excel

@negative
  Scenario: import file excel dengan format tanggal menjadi "date" dd/mm/yyy
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file excel cuti format tanggal invalid
    And User klik button import
    Then Sistem menampilkan pesan gagal import file excel

@negative
  Scenario: Import file excel dengan jenis extension tahun lama "97-2003"
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman import cuti
    When User memilih file cuti format excel lama
    And User klik button import
    Then Sistem menampilkan pesan file harus berupa file excel
