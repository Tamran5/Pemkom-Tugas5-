data_mahasiswa = []

while True:
    nim = int(input('masukkan NIM'))
    nama = input('masukkan Nama')
    
    biodata ={'nim': nim, 'nama' : nama}
    data_mahasiswa.append(biodata)
    
    option = input('ingin tambah lagi? (y/n) ')
    
    if option == 'n':
        break
    
for i in data_mahasiswa:
    print(i['nim'], i('nama'))
    