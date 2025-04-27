import pandas as pd

data_nilai = [
    [90,60],
    [50,50],
    [70,80]
]

df = pd.DataFrame(data_nilai,
                  index=['mahasiswa 1', 'mahasiswa 2', 'mahasiswa 3'],
                  columns=['algoritma dan data struktur data 2', 'matematika numerik'])
print(df)

rata_rata_matkul= df.mean(axis=0)
print(rata_rata_matkul)