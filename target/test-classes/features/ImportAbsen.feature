Feature: Import Absen Hadir
  Sebagai admin
  Saya ingin melakukan import absen hadir
  Agar data absensi masuk ke sistem

  @Positif
  Scenario: Import file excel valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman import absen
    When User klik button choose file dan pilih file excel valid
    And User klik button import
    Then Sistem menampilkan message "Berhasil import excel"
#
#  @Positif
#  Scenario: Download template import absen
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman import absen
#    When User klik button download template
#    Then Sistem berhasil mengunduh template berupa file excel
#
#  @Negatif
#  Scenario: Import file dengan ekstensi excel
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman import absen
#    When User pilih file dengan ekstensi salah
#    And User klik button import format salah
#    Then Sistem menampilkan error message "*File harus berupa file Excel (.xls atau .xlsx)"
#
#  @Negatif
#  Scenario: Import tanpa memilih file
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman import absen
#    And User klik button import tanpa memilih file
#    Then Sistem menampilkan pop up message "Please select a file."
