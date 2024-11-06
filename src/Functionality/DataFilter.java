package Functionality;


import Database.Student;

import java.util.List;
import java.util.stream.Collectors;


public class DataFilter {
    private List<Student> filteredDataList;
    private List<Student> dataList;

    public DataFilter(List<Student> dataList) {
        this.dataList = dataList;
        this.filteredDataList = dataList; //Initial Data
    }


    public List<Student> filterData(String searchText) {
        if (searchText.isEmpty()) {
            filteredDataList = dataList;
        } else {

            filteredDataList = dataList.stream()
                    .filter(d -> d.getName().toLowerCase().contains(searchText) ||
                            d.getId().contains(searchText) ||
                            d.getClassName().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
        }

        return filteredDataList;
    }

    //Temp Method to display results filtered
    public void displayFilteredData() {
        System.out.println("Filtered Results:");
        for (Student data : filteredDataList) {
            System.out.println(data);
        }
        System.out.println("-----");
    }
}