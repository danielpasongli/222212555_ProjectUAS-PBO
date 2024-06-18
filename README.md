Deskripsi
Sistem Pengaduan Kerusakan Barang adalah aplikasi berbasis Java yang memungkinkan pengguna untuk melaporkan kerusakan barang. Aplikasi ini menggunakan database SQLite untuk menyimpan data pengguna dan pengaduan kerusakan.

Fitur
Registrasi Pengguna: Pengguna dapat mendaftar dengan nama pengguna, NIP, dan kata sandi.
Login: Pengguna dapat login dengan nama pengguna dan kata sandi.
Pengaduan Kerusakan: Pengguna dapat mengirim laporan pengaduan kerusakan barang.
Profil Akun: Pengguna dapat melihat dan memperbarui informasi akun mereka.
Dashboard: Tampilan utama setelah login, menampilkan berbagai fitur aplikasi.
Prasyarat
Java Development Kit (JDK) versi 8 atau lebih baru
Maven (jika menggunakan Maven untuk manajemen dependensi)
SQLite JDBC Driver (sudah disertakan dalam proyek)

Penggunaan
Registrasi: Buka aplikasi dan pilih opsi untuk mendaftar. Isi formulir registrasi dengan nama pengguna, NIP, dan kata sandi.
Login: Masukkan nama pengguna dan kata sandi Anda, kemudian klik tombol login.
*jika ingin mencoba, username: admin, password: admin
Mengirim Pengaduan: Setelah login, pilih opsi untuk mengirim pengaduan. Isi formulir pengaduan dan kirimkan.
Profil Akun: Lihat dan perbarui informasi akun Anda di halaman profil akun.
Dashboard: Gunakan dashboard untuk mengakses fitur-fitur aplikasi lainnya.
Struktur Proyek
src/main/java/uas/controller: Direktori yang berisi kelas-kelas controller untuk menangani logika aplikasi.
src/main/java/uas/database: Direktori yang berisi kelas-kelas untuk koneksi dan operasi database.
src/main/java/uas/model: Direktori yang berisi kelas-kelas model yang mewakili entitas data.
src/main/java/uas/view: Direktori yang berisi kelas-kelas view untuk antarmuka pengguna.
