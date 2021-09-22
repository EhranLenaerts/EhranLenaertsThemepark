Describe "Check results file is present" {
    It "Check results file is present" {
        Test-Path src/main | Should -Be $true
    }
}
