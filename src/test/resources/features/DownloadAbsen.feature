Feature: Download Absen

  @positive
  Scenario: Download absen berdasarkan input lengkap
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    And User memilih Start Date dan End Date valid1
    When User memilih Nama User valid1
    When User memilih NIK User valid1
    When User memilih Upliner valid1
    When User memilih Divisi valid1
    When User memilih Unit valid1
    When User memilih Tipe Report valid1
    When User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

  @positive
  Scenario: Download absen berdasarkan tanggal
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid2
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

  @positive
  Scenario: Download absen berdasarkan tanggal dan tipe report valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid3
    And User memilih Tipe Report valid3
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

  @positive
  Scenario: Download absen berdasarkan tanggal, nama, dan tipe report
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid4
    And User memilih Nama User valid4
    And User memilih Tipe Report valid4
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

   @positive
  Scenario: Download absen berdasarkan tanggal, NIK, dan tipe report valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid5
    And User memilih NIK User valid5
    And User memilih Tipe Report valid5
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

    @positive
  Scenario: Download absen berdasarkan tanggal, nama upliner, dan tipe report valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid6
    And User memilih Nama Upliner valid6
    And User memilih Tipe Report valid6
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen
  

  @positive
  Scenario: Download absen berdasarkan tanggal, divisi, unit, dan tipe report valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman download absen
    When User memilih Start Date dan End Date valid7
    And User memilih Divisi valid7
    And User memilih Unit valid7
    And User memilih Tipe Report valid7
    And User klik button Download
    Then Sistem berhasil mengunduh file excel rekap absen

  #   @negative
  # Scenario: Download absen berdasarkan input lengkap invalid
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid1
  #   When User memilih NIK invalid1
  #   And User memilih Nama User invalid1
  #   And User memilih Upliner invalid1
  #   And User memilih Divisi invalid1
  #   And User memilih Unit invalid1
  #   And User memilih Tipe Report invalid1
  #   And User klik button Download
  #   Then Sistem gagal mengunduh file dan menampilkan pesan maaf data yang dicari invalid1

  #    @negative
  # Scenario: Download absen berdasarkan tanggal & NIK invalid
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid2
  #   When User memilih NIK invalid2
  #   And User memilih Tipe Report invalid2
  #   And User klik button Download
  #   Then Sistem gagal mengunduh file dan menampilkan pesan maaf data yang dicari invalid2

  # @negative
  # Scenario: Download absen berdasarkan tanggal & nama user invalid
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid3
  #   When User memilih Nama User invalid3
  #   And User memilih Tipe Report invalid3
  #   And User klik button Download
  #   Then Sistem gagal mengunduh file dan menampilkan pesan maaf data yang dicari invalid3

  # @negative
  # Scenario: Download absen berdasarkan tanggal & divisi invalid
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid4
  #   When User memilih Divisi invalid4
  #   And User memilih Tipe Report invalid4
  #   And User klik button Download
  #   Then Sistem gagal mengunduh file dan menampilkan pesan maaf data yang dicari invalid4

  # @negative
  # Scenario: Download absen berdasarkan tanggal & unit invalid
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid5
  #   When User memilih Unit invalid5
  #   And User masuk ke halaman download absen
  #   And User memilih Start Date dan End Date invalid5
  #   When User memilih Unit invalid5
  #   And User memilih Tipe Report invalid5
  #   And User klik button Download
  #   Then Sistem gagal mengunduh file dan menampilkan pesan maaf data yang dicari invalid5

