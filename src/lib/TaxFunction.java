package lib;
import lib.TaxConstants;


public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
    if (numberOfMonthWorking > 12) {
        throw new IllegalArgumentException("Jumlah bulan bekerja tidak boleh lebih dari 12");
    }

    if (numberOfChildren > 3) {
        numberOfChildren = 3;
    }

    int nonTaxable = TaxConstants.BASIC_NON_TAXABLE;
    if (isMarried) {
        nonTaxable += TaxConstants.MARRIAGE_ADDITION + (numberOfChildren * TaxConstants.CHILD_ADDITION);
    }

    int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - nonTaxable;
    int tax = (int) Math.round(0.05 * taxableIncome);

    return tax < 0 ? 0 : tax;
}

	
}
